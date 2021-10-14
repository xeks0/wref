package ru.wref.service

import junit.framework.Assert.assertEquals
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import ru.wref.model.Post
import ru.wref.repository.PostRepository
import ru.wref.repository.UserRepository
import javax.inject.Inject

@FlywayTest(locationsForMigrate = ["db/migration"])
@SpringBootTest
class MigrationStackExchangeTest {

  @Inject
  lateinit var migrationStackExchange: MigrationStackExchange;

  @Inject
  lateinit var userRepository: UserRepository;

  @Inject
  lateinit var postRepository: PostRepository;

  @Test
  @Transactional
  fun testCreateAccount() {
    migrationStackExchange.migrationMovie()
    assert(userRepository.findAll().size > 0)
    val post: Post? =postRepository.findByIdOrNull(3);
    post?.comments?.let { assertEquals(it.size, post.commentCount) }
  }
}
