package ru.wref.service

import junit.framework.Assert.assertEquals
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
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
  fun testMigration() {
    migrationStackExchange.migrationMovie()
    assert(userRepository.findAll().size > 0)
    val post: Post? =postRepository.getOneById(3);
    assertEquals(post?.comments?.size, 3)
    assertEquals(post?.tagsList?.size, 2)
  }
}
