package ru.wref.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Account

@Repository
@Transactional
interface AccountRepository: CrudRepository<Account, Long>, JpaQueryDslPredicateRepository<Account,Long>,PagingAndSortingRepository<Account, Long> {

}
