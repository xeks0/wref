package ru.wref.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.User

@Repository
@Transactional
interface UserRepository : CrudRepository<User, Long>, JpaQueryDslPredicateRepository<User, Long>, PagingAndSortingRepository<User, Long> {
  fun findOneById(valueAsLong: Long): User?

  /**
   * There is function to get the last record.
   */
  fun findTopByOrderByIdDesc(): User?
  fun findTopByOrderByIsTranslateAsc(): User?
  fun getOneById(id: Long): User?

}
