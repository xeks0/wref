package ru.wref.service

import junit.framework.Assert.assertEquals
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
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

  @Inject
  lateinit var migrationProductionDataXML: MigrationProductionDataXML;

  @Test
  fun migrationSediling(){
    for (i in 1..10){
      migrationProductionDataXML.exportData("data_meta",5000);
    }
  }
  /**
   * 8 минут перевод в стеднем 100 постов 503 секунд, на 1 перевод 5 секунд.
   *
   * 4 минуты если распаралелить на 4 потока, прирост в 2 раза меньше времени
   */
  @Test
  fun testMigration() {
    migrationStackExchange.migrationMovie("data_meta")
    assert(userRepository.findAll().size > 0)
    val post: Post? =postRepository.getOneById(3);
    assertEquals(post?.comments?.size, 3)
    assertEquals(post?.tagsList?.size, 2)

    var posts:List<Post> = postRepository.findAll();

    var character: Int = 0;
    for (post: Post in posts){
      character += post.title?.length ?: 0;
      character += post.body?.length ?: 0;
    }
    println("Total caracters in test : " + character +" symbols" )

    for (i in 1..50) {
      migrationProductionDataXML.translate(10)
    }

    println("Ok" )
  }


}

