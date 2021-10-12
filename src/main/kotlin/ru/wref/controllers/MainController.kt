package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

  @GetMapping(name = "/")
  fun index():String{
    return "Hello";
  }
}
