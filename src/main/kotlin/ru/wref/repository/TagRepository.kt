package ru.wref.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Tag

@Repository
@Transactional
interface TagRepository : CrudRepository<Tag, Long>, JpaQueryDslPredicateRepository<Tag, Long>, PagingAndSortingRepository<Tag, Long> {

  fun findOneByTagName(tag:String): Tag?
}
