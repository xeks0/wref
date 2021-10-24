package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.utils.OffsetBasedPageRequest
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
    posts.slug = (posts.title?.trim()?.replace(" ", "_")?.lowercase() ?: "") + posts.id.toString()
    posts.isTranslate = 0;
    return posts;
  }
  fun createPostForce(post: Post): Post {
    return postRepository.save(post);
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

  fun getPostFromId(id: Long): Post? {
    return postRepository.getOneById(id);
  }

  fun findLastPost(): Post? {
    return postRepository.findTopByOrderByIdDesc()

  }

  fun getPostFromUserAndDateBefore(post: Post): Post? {
    var posts: List<Post> = postRepository.findByOwnerUserAndCreationDateLessThan(post.ownerUser, post.creationDate)
    if (posts.isNotEmpty()) {
      return posts[0]
    }
    return null
  }

  fun getPostFromUserAndDateAfter(post: Post): Post? {
    var posts: List<Post> = postRepository.findByOwnerUserAndCreationDateGreaterThan(post.ownerUser, post.creationDate)
    if (posts.isNotEmpty()) {
      return posts[0]
    }
    return null
  }

  fun getLastPostTranslate(): Post? {
   return postRepository.findTopByOrderByIsTranslateAsc()
  }

  fun getPostFeromStartAndLimit(start:Post, end: Int): Page<Post> {
    val pageable: Pageable = OffsetBasedPageRequest(start.id!!.toInt(), end)
    return postRepository.findAll(pageable)
  }

}
