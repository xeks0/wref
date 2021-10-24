package ru.wref.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Comment

@Repository
@Transactional
interface CommentRepository : CrudRepository<Comment, Long>, JpaQueryDslPredicateRepository<Comment, Long>, PagingAndSortingRepository<Comment, Long> {
  fun findOneById(longValue: Long): Comment?
  fun findTopByOrderByIdDesc(): Comment?
  fun findTopByOrderByIsTranslateAsc(): Comment?
  fun getOneById(id: Long): Comment?

}
