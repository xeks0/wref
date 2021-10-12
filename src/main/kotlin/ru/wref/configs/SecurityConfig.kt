package ru.wref.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
  @Bean
  fun encoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.inMemoryAuthentication()
      .withUser("admin")
      .password(encoder().encode("pass"))
      .roles("MANAGER", "ADMIN")
      .and()
      .withUser("manager")
      .password(encoder().encode("pass"))
      .roles("MANAGER")
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http.httpBasic().disable().anonymous()
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
      .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN", "DOCTOR")
      .and()
      .csrf().disable()
      .formLogin()
  }

}
