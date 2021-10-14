package ru.wref.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.CommentComponent
import ru.wref.components.UserComponent
import ru.wref.components.PostComponent
import ru.wref.components.TagComponent
import ru.wref.dto.CommentsList
import ru.wref.dto.UsersList
import ru.wref.dto.PostList
import ru.wref.dto.TagList
import javax.inject.Inject
import javax.xml.bind.JAXBContext

@Service
class MigrationStackExchange {

  @Inject
  lateinit var userComponent: UserComponent;

  @Inject
  lateinit var tagComponent: TagComponent;

  @Inject
  lateinit var postComponent: PostComponent;

  @Inject
  lateinit var commentComponent : CommentComponent;

  @Transactional
  fun migrationMovie(): Boolean? {
    return executeUser() == true && executeTag() == true && executePost() == true && executeComment();
  }

  private fun executeComment(): Boolean {
    val comments: CommentsList = getResources(JAXBContext.newInstance(CommentsList::class.java),"data/Comments.xml") as CommentsList
    return commentComponent.createCommentFromList(comments).isNotEmpty();

  }

  fun executeUser():Boolean?{
    val users: UsersList = getResources(JAXBContext.newInstance(UsersList::class.java),"data/Users.xml") as UsersList
    return userComponent.createUserFromList(users).isNotEmpty();
  }

  fun executeTag():Boolean?{
    val tagList: TagList = getResources(JAXBContext.newInstance(TagList::class.java),"data/Tags.xml") as TagList
    return tagComponent.createTagsFromList(tagList).isNotEmpty();
  }

  fun executePost():Boolean?{
    val tagList: PostList = getResources(JAXBContext.newInstance(PostList::class.java),"data/Posts.xml") as PostList
    return postComponent.createPostsFromList(tagList).isNotEmpty();
  }

  private fun getResources(jaxbContext : JAXBContext, file: String): Any? {
    val path = this.javaClass.classLoader.getResource(file);
    val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
    val value = jaxbUnmarshaller.unmarshal(path);
    return value
  }

}
