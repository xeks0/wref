package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class AdminController {

  @GetMapping( "/admin")
  fun admin(principal: Principal): String{
    return "Hello ${principal.name}";
  }
}
