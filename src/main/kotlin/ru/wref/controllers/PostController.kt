package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.wref.components.PostComponent
import ru.wref.model.Post
import ru.wref.respounce.PostResponse
import javax.inject.Inject

@RestController
class PostController {

  @Inject
  lateinit var postComponent: PostComponent;

  @GetMapping("/post/{id}", produces = ["application/json"])
  @ResponseBody
  fun getPostId(@PathVariable id: Long): PostResponse {
    val post: Post? = postComponent.getPostFromId(id);
    if (post != null) {
      val left: Post? = getLeftPost(post)
      val right: Post? = getRightPost(post)
      return PostResponse(post, left, right)
    }
    return PostResponse(null, null, null)
  }


  private fun getRightPost(post: Post): Post? {
    return postComponent.getPostFromUserAndDateAfter(post)
      ?: return post.id?.plus(1)?.let { postComponent.getPostFromId(it) }
  }


  private fun getLeftPost(post: Post): Post? {
    return postComponent.getPostFromUserAndDateBefore(post)
      ?: return post.id?.minus(1)?.let { postComponent.getPostFromId(it) }
  }

}
