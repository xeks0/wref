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
@Transactional
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
  fun migrationMovie(path:String): Boolean? {
    return executeUser(getUsers(path)) == true && executeTag(getTags(path)) == true && executePost(getPosts(path)) == true && executeComment(getComments(path));
  }

  fun getComments(path: String):CommentsList{
    return getResources(JAXBContext.newInstance(CommentsList::class.java),"$path/Comments.xml") as CommentsList
  }

  fun executeComment(comments: CommentsList): Boolean {
    return commentComponent.createCommentFromList(comments).isNotEmpty();

  }

  fun getUsers(path: String):UsersList{
   return getResources(JAXBContext.newInstance(UsersList::class.java),"$path/Users.xml") as UsersList
  }

  fun executeUser(users: UsersList):Boolean?{
    return userComponent.createUserFromList(users).isNotEmpty();
  }

  fun getTags(path: String):TagList{
    return  getResources(JAXBContext.newInstance(TagList::class.java), "$path/Tags.xml") as TagList
  }

  fun executeTag(tagList: TagList):Boolean?{
    return tagComponent.createTagsFromList(tagList).isNotEmpty();
  }

  fun getPosts(path: String):PostList{
    return getResources(JAXBContext.newInstance(PostList::class.java),"$path/Posts.xml") as PostList
  }

  fun executePost(postList: PostList ):Boolean?{
    return postComponent.createPostsFromList(postList).isNotEmpty();
  }

  private fun getResources(jaxbContext : JAXBContext, file: String): Any? {
    val path = this.javaClass.classLoader.getResource(file);
    val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
    val value = jaxbUnmarshaller.unmarshal(path);
    return value
  }

}
