package ru.wref.repository

import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.wref.model.User
import javax.inject.Inject

@FlywayTest(locationsForMigrate = ["db/migration"])
@SpringBootTest
//@AutoConfigureEmbeddedDatabase
class UserRepositoryTest {

  @Inject
  lateinit var userRepository: UserRepository;

  @Test
  fun testCreateAccount() {
    val user: User = User();
    user.id = 1;
    user.displayName = "New Name"
    userRepository.save(user)
    assert(userRepository.findAll().size > 0)
  }
}
