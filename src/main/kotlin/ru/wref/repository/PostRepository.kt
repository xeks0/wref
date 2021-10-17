package ru.wref.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Post

@Repository
@Transactional
interface PostRepository : CrudRepository<Post, Long>, JpaQueryDslPredicateRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

  @EntityGraph(attributePaths = ["comments","tagsList"])
  fun getOneById(longValue: Long): Post?

}
