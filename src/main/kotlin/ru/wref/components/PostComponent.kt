package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.utils.OffsetBasedPageRequest
import ru.wref.components.utils.PostTypeUtils
import ru.wref.dto.PostDTO
import ru.wref.dto.PostList
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.TagSerialisation
import ru.wref.mapper.UserSerialisation
import ru.wref.model.Post
import ru.wref.model.PostProxy
import ru.wref.model.TagProxy
import ru.wref.model.UserProxy
import java.util.*


@Component
class PostComponent : DataComponent<PostDTO, Post>() {


  private fun updatePost(posts: Post, startIds: Int, type: Post.Type): Post {
    posts.id = posts.id?.plus(startIds)
    posts.parent = posts.parentId?.post
    posts.acceptedAnswer = posts.acceptedAnswerId?.post
    posts.tagsList = posts.tags?.tags
    posts.ownerUser = posts.ownerUserId?.user;
    posts.lastEditorUser = posts.lastEditorUserId?.user;
    posts.slug = (posts.title?.trim()?.replace(" ", "_")?.lowercase() ?: "") + posts.id.toString()
    posts.isTranslate = 0;
    posts.type = type;
    return posts;
  }
  fun createPostForce(post: Post): Post {
    return postRepository.save(post);
  }

  fun createPostFromModel(post: Post, startIds: Int, type: Post.Type): Post {
    return postRepository.save(updatePost(post,startIds,type));
  }

  fun createPostFromDto(postDTO: PostDTO, startIds: Int, type: Post.Type): Post {
    return createPostFromModel(prepareDtoFrom(postDTO, Post::class.java) as Post? as Post,startIds,type)
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
  fun createPostsFromList(post: PostList, startIds: Int, type: Post.Type): List<Post> {
    mapper = jacksonObjectMapper();
    mapper = moduleRegistrationUser.registerModule(UserProxy::class.java, UserSerialisation(userRepository,startIds), mapper)
    mapper = moduleRegistrationPost.registerModule(PostProxy::class.java, PostSerialisation(postRepository,startIds), mapper)
    mapper = moduleRegistrationTag.registerModule(TagProxy::class.java, TagSerialisation(tagRepository), mapper)
    val posts: MutableList<Post> = mutableListOf()
    for (post: PostDTO in post.postDTOList!!) {
      posts.add(createPostFromDto(post,startIds,type))
    }
    return posts;
  }

  fun getPostFromId(id: Long, type: String): Post? {

    return postRepository.getOneByIdAndType(id, PostTypeUtils.getTypeByString(type));
  }
  fun getPostFromId(id: Long, type: Post.Type): Post? {
    return postRepository.getOneByIdAndType(id, type);
  }

  fun findLastPost(type: Post.Type): Post? {
    return postRepository.findTopByTypeOrderByIdDesc(type)

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

  fun getLastPostTranslate(type: Post.Type): Post? {
   return postRepository.findTopByTypeOrderByIsTranslateAsc(type)
  }

  fun getPostFeromStartAndLimit(start:Post, end: Int): Page<Post> {
    val pageable: Pageable = OffsetBasedPageRequest(start.id!!.toInt().toLong(), end, Sort.Direction.DESC,"id")
    return postRepository.findAllByIsTranslate(0,pageable)
  }

  fun getPostPage(id: Long, type: String): Page<Post> {
    val page: Pageable = PageRequest.of(id.toInt(), 10)
    return postRepository.findAllByPostTypeIdAndType(page,1,PostTypeUtils.getTypeByString(type))
  }

  fun updatePostTranslate(id: Long?, body: String?, title: String?) {
    var post: Optional<Post> = postRepository.findById(id!!)
    if(post.isPresent){
      var postUpdate: Post = post.get();
      postUpdate.titleRu = title;
      postUpdate.bodyRu = body;
      postUpdate.isTranslate = 1;
      postRepository.save(postUpdate)
    }
  }

  fun findPostByParent(post: Post, typeByString: Post.Type): List<Post> {
    return postRepository.findByParentAndType(post,typeByString);
  }


  fun findAllByPostTypeAndType(i: Int, type: Post.Type): List<Post> {
    return postRepository.findAllByPostTypeIdAndType(1,type)
  }

}
