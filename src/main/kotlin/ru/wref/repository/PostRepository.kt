package ru.wref.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Post
import ru.wref.model.User
import java.util.*

@Repository
@Transactional
interface PostRepository : CrudRepository<Post, Long>, JpaQueryDslPredicateRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

  @EntityGraph(attributePaths = ["comments","tagsList"])
  fun getOneById(longValue: Long): Post?
  fun findTopByOrderByIdDesc(): Post?
  fun findByOwnerUserAndCreationDateLessThan(ownerUser: User?, creationDate: Date?):List<Post>
  fun findByOwnerUserAndCreationDateGreaterThan(ownerUser: User?, creationDate: Date?): List<Post>
  fun findTopByOrderByIsTranslateDesc(): Post?
  fun findTopByOrderByIsTranslateAsc(): Post?
  fun findAllByIsTranslate(i: Int, pageable: Pageable): Page<Post>
  fun findAllByParentIsNull( pageable: Pageable): Page<Post>
  fun findAllByPostTypeId(page: Pageable, i: Int): Page<Post>
  fun findAllByPostTypeId(i: Int): List<Post>
  fun findByParent(post: Post): List<Post>
  fun getOneByIdAndType(id: Long, typeEn: Post.Type): Post?
  fun findByParentAndType(post: Post, typeByString: Post.Type): List<Post>
  fun findAllByPostTypeIdAndType(page: Pageable, i: Int, typeByString: Post.Type?): Page<Post>
  fun findAllByPostTypeIdAndType(i: Int, typeByString: Post.Type?): List<Post>
  fun findTopByTypeOrderByIdDesc(type: Post.Type): Post?
  fun findTopByTypeOrderByIsTranslateAsc(type: Post.Type): Post?
}
