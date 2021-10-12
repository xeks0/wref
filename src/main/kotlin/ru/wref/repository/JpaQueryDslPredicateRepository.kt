package ru.wref.repository

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Predicate
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface JpaQueryDslPredicateRepository<T, ID : Serializable?> : JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
  override fun findAll(vararg orders: OrderSpecifier<*>?): List<T>
  override fun findAll(predicate: Predicate): List<T>
  override fun findAll(predicate: Predicate, vararg orders: OrderSpecifier<*>?): List<T>
  override fun findAll(predicate: Predicate, sort: Sort): List<T>
}
