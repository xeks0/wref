package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.utils.OffsetBasedPageRequest
import ru.wref.dto.CommentDTO
import ru.wref.dto.CommentsList
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.UserSerialisation
import ru.wref.model.Comment
import ru.wref.model.PostProxy
import ru.wref.model.UserProxy

@Component
class CommentComponent : DataComponent<CommentDTO, Comment>() {

  private fun updateComment(comment: Comment, startIds: Int): Comment {
    comment.post = comment.postId?.post;
    comment.user = comment.UserId?.user;
    comment.id = comment.id?.plus(startIds)
    return comment;
  }

  fun createCommentFromModel(comment: Comment, startIds: Int): Comment {
    return commentRepository.save(updateComment(comment,startIds));
  }
  fun createPostForce(comment: Comment): Comment {
    return commentRepository.save(comment);
  }
  fun createCommentFromDto(CommentDTO: CommentDTO, startIds: Int): Comment {
    return createCommentFromModel(prepareDtoFrom(CommentDTO, Comment::class.java) as Comment? as Comment,startIds)
  }

  @Transactional
  fun createCommentFromList(comment: CommentsList, startIds: Int): List<Comment> {
    mapper = jacksonObjectMapper();
    mapper = moduleRegistrationUser.registerModule(UserProxy::class.java, UserSerialisation(userRepository, startIds), mapper)
    mapper = moduleRegistrationPost.registerModule(PostProxy::class.java, PostSerialisation(postRepository, startIds), mapper)
    val comments: MutableList<Comment> = mutableListOf()
    for (post: CommentDTO in comment.commentDTOList!!) {
      comments.add(createCommentFromDto(post,startIds))
    }
    return comments;
  }

  fun findLastComment(): Comment? {
   return commentRepository.findTopByOrderByIdDesc();
  }

  fun getCommentFromId(id: Long): Comment? {
    return commentRepository.getOneById(id);
  }

  fun getPostFromStartAndLimit(start: Comment, end: Int): Page<Comment> {
    val pageable: Pageable = OffsetBasedPageRequest(start.id!!.toInt(), end)
    return commentRepository.findAll(pageable)

  }

  fun updateCommentTranslate(comments: List<ru.wref.respounce.Comment>) {
    for (comment in comments){
     val commentDb: Comment? = commentRepository.findOneById(comment.id)
      commentDb?.textRu = comment.text;
      if (commentDb != null) {
        commentDb.isTranslate = 1
      };
      if(commentDb!=null){
        commentRepository.save(commentDb)
      }
    }
  }


}
