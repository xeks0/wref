package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.PostDTO
import ru.wref.dto.PostList
import ru.wref.mapper.AccountSerialisation
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.TagSerialisation
import ru.wref.model.*
import ru.wref.repository.PostRepository
import javax.inject.Inject

@Component
class PostComponent: DataComponent<PostDTO,Post>() {

  @Inject
  lateinit var moduleRegistrationAccount: ModuleRegistration<AccountProxy>;

  @Inject
  lateinit var moduleRegistrationTag: ModuleRegistration<TagProxy>;

  @Inject
  lateinit var moduleRegistrationPost: ModuleRegistration<PostProxy>;


  @Inject
  lateinit var postRepository: PostRepository;

  fun createPostFromModel(post: Post): Post {
    return postRepository.save(post);
  }

  fun createPostFromDto(postDTO: PostDTO): Post {
    return createPostFromModel(prepareDtoFrom(postDTO,Post::class.java) as Post? as Post)
  }

  /**
   * Create posts from list of received data from xml
   *
   */
  @Transactional
  fun createPostsFromList(post: PostList): List<Post> {
    mapper = moduleRegistrationAccount.registerModule(AccountProxy::class.java, AccountSerialisation(accountRepository),mapper)
    mapper = moduleRegistrationPost.registerModule(PostProxy::class.java,PostSerialisation(postRepository),mapper)
    mapper = moduleRegistrationTag.registerModule(TagProxy::class.java, TagSerialisation(tagRepository),mapper)
    val posts: MutableList<Post> = mutableListOf()
    for (post: PostDTO in post.postDTOList!!) {
      posts.add(createPostFromDto(post));
    }
    return posts;
  }


}
