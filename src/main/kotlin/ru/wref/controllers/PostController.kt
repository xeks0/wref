package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.wref.components.PostComponent
import ru.wref.model.Post
import javax.inject.Inject

@RestController
class PostController {

  @Inject
  lateinit var postComponent: PostComponent;

  @GetMapping("/post/{id}", produces = ["application/json"])
  @ResponseBody
  fun getPostId(@PathVariable id:Long): Post? {
    return postComponent.getPostFromId(id);
  }

}
