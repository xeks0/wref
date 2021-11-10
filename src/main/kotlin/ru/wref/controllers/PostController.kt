package ru.wref.controllers

import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import ru.wref.components.CommentComponent
import ru.wref.components.PostComponent
import ru.wref.components.utils.PostTypeUtils
import ru.wref.model.Post
import ru.wref.respounce.PostResponse
import ru.wref.respounce.PostResponseList
import ru.wref.respounce.PostUpdate
import javax.inject.Inject

@RestController
class PostController {

  @Inject
  lateinit var postComponent: PostComponent;

  @Inject
  lateinit var commentComponent: CommentComponent;

  @GetMapping("/post/{id}/{type}", produces = ["application/json"])
  @ResponseBody
  fun getPostId(@PathVariable id: Long,@PathVariable type: String): PostResponse {
    val post: Post? = postComponent.getPostFromId(id,type);
    if (post != null) {
      val left: Post? = getLeftPost(post,type)
      val right: Post? = getRightPost(post,type)
      val child:List<Post> = getPostParent(post,type);
      return PostResponse(post,child, left, right)
    }
    return PostResponse(null, mutableListOf(),null, null)
  }

  private fun getPostParent(post: Post, type: String): List<Post> {
    return postComponent.findPostByParent(post,PostTypeUtils.getTypeByString(type))
  }

  @GetMapping("/post/page/{id}/{type}", produces = ["application/json"])
  @ResponseBody
  fun getPostPage(@PathVariable id: Long,@PathVariable type: String): PostResponseList {
    val posts:MutableList<PostResponse> = mutableListOf();
    val postPage: Page<Post> = postComponent.getPostPage(id-1,type);
    for (post:Post in postPage.get()){
      posts.add(PostResponse(post, mutableListOf(),null, null))
    }
    return PostResponseList(posts,id.toInt()*10,id.toInt()*10+10,postPage.totalElements, postPage.totalPages,id.toInt())
  }
  @PostMapping("/post/update", produces = ["application/json"])
  @ResponseBody
  fun getPostUpdate(@RequestBody post: PostUpdate): String {

    postComponent.updatePostTranslate(post.id,post.body,post.title)
    commentComponent.updateCommentTranslate(post.comments)

    for (post1:PostUpdate in post.child){
      postComponent.updatePostTranslate(post1.id,post1.body,post1.title)
      commentComponent.updateCommentTranslate(post1.comments)
    }
    println("PostController.getPostUpdate ${post.id}")
    return "OK"
  }

  private fun getRightPost(post: Post, type: String): Post? {
    if(post == null){
      return null
    }
    var i: Long? = post.id?.plus(1L);
    var limit = 1000;
    while (limit>0){
      limit--;
     val post: Post? = i?.let { postComponent.getPostFromId(it, type) };
      if(post!=null &&  post.postTypeId == 1){
        return post
      }
      if (i != null) {
        i++;
        if(i>120000){
          break;
        }
      }else{
        break;
      }
    }
    return null
  }


  private fun getLeftPost(post: Post, type: String): Post? {

    if(post == null){
      return null
    }
    var i: Long? = post.id?.minus(1L);
    var limit = 1000;
    while ( limit>0){
      limit--;
      val post: Post? = i?.let { postComponent.getPostFromId(it, PostTypeUtils.getTypeByString(type)) };
      if(post!=null && post.postTypeId == 1){
        return post
      }
      if (i != null) {
        i--;
        if(i<0){
          break;
        }
      }else{
        break;
      }
    }
    return null
  }

}
