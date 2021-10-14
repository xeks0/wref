package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.CommentDTO
import ru.wref.dto.CommentsList
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.UserSerialisation
import ru.wref.model.Comment
import ru.wref.model.PostProxy
import ru.wref.model.UserProxy

@Component
class CommentComponent : DataComponent<CommentDTO, Comment>() {

  private fun updateComment(comment: Comment): Comment {
    comment.post = comment.postId?.post;
    comment.user = comment.UserId?.user;
    return comment;
  }

  fun createCommentFromModel(comment: Comment): Comment {
    return commentRepository.save(updateComment(comment));
  }

  fun createCommentFromDto(CommentDTO: CommentDTO): Comment {
    return createCommentFromModel(prepareDtoFrom(CommentDTO, Comment::class.java) as Comment? as Comment)
  }

  @Transactional
  fun createCommentFromList(comment: CommentsList): List<Comment> {
    mapper = jacksonObjectMapper();
    mapper = moduleRegistrationUser.registerModule(UserProxy::class.java, UserSerialisation(userRepository), mapper)
    mapper = moduleRegistrationPost.registerModule(PostProxy::class.java, PostSerialisation(postRepository), mapper)
    val comments: MutableList<Comment> = mutableListOf()
    for (post: CommentDTO in comment.commentDTOList!!) {
      comments.add(createCommentFromDto(post))
    }
    return comments;
  }
}
