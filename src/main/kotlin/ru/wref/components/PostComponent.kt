package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.PostDTO
import ru.wref.dto.PostList
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.TagSerialisation
import ru.wref.mapper.UserSerialisation
import ru.wref.model.Post
import ru.wref.model.PostProxy
import ru.wref.model.TagProxy
import ru.wref.model.UserProxy

@Component
class PostComponent : DataComponent<PostDTO, Post>() {


  private fun updatePost(posts: Post): Post {
    posts.parent = posts.parentId?.post
    posts.acceptedAnswer = posts.acceptedAnswerId?.post
    posts.tagsList = posts.tags?.tags
    posts.ownerUser = posts.ownerUserId?.user;
    posts.lastEditorUser = posts.lastEditorUserId?.user;
    return posts;
  }

  fun createPostFromModel(post: Post): Post {
    return postRepository.save(updatePost(post));
  }

  fun createPostFromDto(postDTO: PostDTO): Post {
    return createPostFromModel(prepareDtoFrom(postDTO, Post::class.java) as Post? as Post)
  }

  /**
   * Create posts from list of received data from xml
   * Add saving posts with re-sending to the database
   * after the first save in order to well miss referring
   * objects such as ParentId when they can be created
   * and edited after creating the main post used trick
   * proxy stand in entity
   */
  @Transactional
  fun createPostsFromList(post: PostList): List<Post> {
    mapper = jacksonObjectMapper();
    mapper = moduleRegistrationUser.registerModule(UserProxy::class.java, UserSerialisation(userRepository), mapper)
    mapper = moduleRegistrationPost.registerModule(PostProxy::class.java, PostSerialisation(postRepository), mapper)
    mapper = moduleRegistrationTag.registerModule(TagProxy::class.java, TagSerialisation(tagRepository), mapper)
    val posts: MutableList<Post> = mutableListOf()
    for (post: PostDTO in post.postDTOList!!) {
      posts.add(createPostFromDto(post))
    }
    return posts;
  }


}
