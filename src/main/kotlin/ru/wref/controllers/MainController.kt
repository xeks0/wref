package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.wref.components.PostComponent
import javax.inject.Inject

@RestController
class MainController {

  @Inject
  lateinit var postComponent: PostComponent;

  @GetMapping("/")
  fun index(): String {
    return "Hello";
  }

}
