package ru.wref.service

import org.springframework.stereotype.Service
import ru.wref.components.CommentComponent
import ru.wref.components.PostComponent
import ru.wref.components.TagComponent
import ru.wref.components.UserComponent
import ru.wref.dto.*
import ru.wref.model.Comment
import ru.wref.model.Post
import ru.wref.model.Tag
import ru.wref.model.User
import javax.inject.Inject

@Service
open class MigrationProductionDataXML {

  @Inject
  lateinit var tagComponent: TagComponent;

  @Inject
  lateinit var migrationStackExchange: MigrationStackExchange;

  @Inject
  lateinit var postComponent: PostComponent;

  @Inject
  lateinit var userComponent: UserComponent;

  @Inject
  lateinit var commentComponent: CommentComponent;

  fun exportData(path: String, maxInTry: Int) {
    if (exportUser(path, maxInTry) != -1) {
      return;
    }
    if (exportTag(path, maxInTry) != -1) {
      return;
    }
    if (exportPost(path, maxInTry) != -1) {
      return;
    }
    if (exportComment(path, maxInTry) != -1) {
      return;
    }
  }

  private fun exportComment(path: String, maxInTry: Int): Int {
    val usersList: CommentsList = migrationStackExchange.getComments(path)
    val lastUser: Comment? = commentComponent.findLastComment();

    if (lastUser == null) {
      val userDTOs: List<CommentDTO> = siblistComment(usersList, 0, maxInTry)
      createComments(usersList, userDTOs)
      return 1
    }
    val indexOfLastUser: Int = getLatIndexComment(usersList, lastUser);
    if (indexOfLastUser == -1) {
      return -1;
    }
    if (indexOfLastUser == usersList.commentDTOList.size - 1) {
      return -1;
    }
    val userDTOs: List<CommentDTO> = siblistComment(usersList, indexOfLastUser + 1, maxInTry + indexOfLastUser)
    createComments(CommentsList(), userDTOs)
    return 1;
  }

  private fun exportPost(path: String, maxInTry: Int): Int {
    val usersList: PostList = migrationStackExchange.getPosts(path)
    val lastUser: Post? = postComponent.findLastPost();

    if (lastUser == null) {
      val userDTOs: List<PostDTO> = siblistPost(usersList, 0, maxInTry)
      createPosts(usersList, userDTOs)
      return 1
    }
    val indexOfLastUser: Int = getLatIndexPost(usersList, lastUser);
    if (indexOfLastUser == -1) {
      return -1;
    }
    if (indexOfLastUser == usersList.postDTOList.size - 1) {
      return -1;
    }
    val userDTOs: List<PostDTO> = siblistPost(usersList, indexOfLastUser + 1, maxInTry + indexOfLastUser)
    createPosts(PostList(), userDTOs)
    return 1;
  }


  private fun exportTag(path: String, maxInTry: Int): Int {
    val usersList: TagList = migrationStackExchange.getTags(path)
    val lastUser: Tag? = tagComponent.findLastTag();

    if (lastUser == null) {
      val userDTOs: List<TagDTO> = siblistTag(usersList, 0, maxInTry)
      createTags(usersList, userDTOs)
      return 1
    }
    val indexOfLastUser: Int = getLatIndexTag(usersList, lastUser);
    if (indexOfLastUser == -1) {
      return -1;
    }
    if (indexOfLastUser == usersList.tagDTOList.size - 1) {
      return -1;
    }
    val userDTOs: List<TagDTO> = siblistTag(usersList, indexOfLastUser + 1, maxInTry + indexOfLastUser)
    createTags(TagList(), userDTOs)
    return 1;
  }

  private fun exportUser(path: String, maxInTry: Int): Int {
    val usersList: UsersList = migrationStackExchange.getUsers(path)
    val lastUser: User? = userComponent.findLastUser();

    if (lastUser == null) {
      val userDTOs: List<UserDTO> = siblistUser(usersList, 0, maxInTry)
      createUsers(usersList, userDTOs)
      return 1
    }
    val indexOfLastUser: Int = getLatIndexUser(usersList, lastUser);
    if (indexOfLastUser == -1) {
      return -1;
    }
    if (indexOfLastUser == usersList.userDTOList!!.size - 1) {
      return -1;
    }
    val userDTOs: List<UserDTO> = siblistUser(usersList, indexOfLastUser + 1, maxInTry + indexOfLastUser)
    createUsers(UsersList(), userDTOs)
    return 1;
  }

  private fun createComments(usersList: CommentsList, userDTOs: List<CommentDTO>) {
    var usersList1 = usersList
    usersList1 = CommentsList();
    usersList1.commentDTOList = userDTOs;
    migrationStackExchange.executeComment(usersList1)
  }

  private fun createPosts(usersList: PostList, userDTOs: List<PostDTO>) {
    var usersList1 = usersList
    usersList1 = PostList();
    usersList1.postDTOList = userDTOs;
    migrationStackExchange.executePost(usersList1)
  }

  private fun createUsers(usersList: UsersList, userDTOs: List<UserDTO>) {
    var usersList1 = usersList
    usersList1 = UsersList();
    usersList1.userDTOList = userDTOs;
    migrationStackExchange.executeUser(usersList1)
  }

  private fun createTags(usersList: TagList, userDTOs: List<TagDTO>) {
    var usersList1 = usersList
    usersList1 = TagList();
    usersList1.tagDTOList = userDTOs;
    migrationStackExchange.executeTag(usersList1)
  }

  private fun getLatIndexComment(usersList: CommentsList, lastUser: Comment): Int {
    for (userDTO: CommentDTO in usersList.commentDTOList) {
      if (userDTO.Id?.toLong() ?: 0 == lastUser.id) {
        return usersList.commentDTOList.indexOf(userDTO)
      }
    }
    return -1;
  }

  private fun getLatIndexPost(usersList: PostList, lastUser: Post): Int {
    for (userDTO: PostDTO in usersList.postDTOList) {
      if (userDTO.id?.toLong() ?: 0 == lastUser.id) {
        return usersList.postDTOList.indexOf(userDTO)
      }
    }
    return -1;
  }

  private fun getLatIndexUser(usersList: UsersList, lastUser: User): Int {
    for (userDTO: UserDTO in usersList.userDTOList) {
      if (userDTO.id?.toLong() ?: 0 == lastUser.id) {
        return usersList.userDTOList.indexOf(userDTO)
      }
    }
    return -1;
  }

  private fun getLatIndexTag(usersList: TagList, lastUser: Tag): Int {
    for (userDTO: TagDTO in usersList.tagDTOList) {
      if (userDTO.TagName.equals(lastUser.tagName)) {
        return usersList.tagDTOList.indexOf(userDTO)
      }
    }
    return -1;
  }

  private fun siblistPost(usersList: PostList, start: Int, last: Int): List<PostDTO> {
    var lastPost: Int = 0;
    lastPost = if (usersList.postDTOList.size < last) {
      usersList.postDTOList.size; } else {
      last;
    }
    return usersList.postDTOList.subList(start, lastPost)
  }

  private fun siblistComment(usersList: CommentsList, start: Int, last: Int): List<CommentDTO> {
    var lastComment: Int = 0;
    lastComment = if (usersList.commentDTOList.size < last) {
      usersList.commentDTOList.size; } else {
      last;
    }
    return usersList.commentDTOList.subList(start, lastComment)
  }


  private fun siblistUser(usersList: UsersList, start: Int, last: Int): List<UserDTO> {
    var lastUser: Int = 0;
    lastUser = if (usersList.userDTOList.size < last) {
      usersList.userDTOList.size; } else {
      last;
    }
    return usersList.userDTOList.subList(start, lastUser)
  }

  private fun siblistTag(usersList: TagList, start: Int, last: Int): List<TagDTO> {
    var lastUser: Int = 0;
    lastUser = if (usersList.tagDTOList.size < last) {
      usersList.tagDTOList.size; } else {
      last;
    }
    return usersList.tagDTOList.subList(start, lastUser)
  }

}
