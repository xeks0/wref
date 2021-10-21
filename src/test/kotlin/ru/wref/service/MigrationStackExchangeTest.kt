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

  @Inject
  lateinit var migrationProductionDataXML: MigrationProductionDataXML;

  @Test
  fun migrationSediling(){
    for (i in 1..10){
      migrationProductionDataXML.exportData("data_meta",3000);
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


    if(true){
      val startTime = translateTest(posts)
      val endTime = System.nanoTime()
      println("Total execution Translate time: " + (endTime - startTime) / 1000000 + "ms" )
    }
    println("Ok" )
  }

  private fun translateTest(posts: List<Post>): Long {
    val startTime = System.nanoTime()

    var postsArray1: List<Post> = posts.subList(0, 25);
    var postsArray2: List<Post> = posts.subList(25, 50);
    var postsArray3: List<Post> = posts.subList(50, 75);
    var postsArray4: List<Post> = posts.subList(75, posts.size);

    var t1: Thread = Thread(GoogleTranslate("en", "ru", postsArray1))
    t1.start();
    var t2: Thread = Thread(GoogleTranslate("en", "ru", postsArray2))
    t2.start();
    var t3: Thread = Thread(GoogleTranslate("en", "ru", postsArray3))
    t3.start();
    var t4: Thread = Thread(GoogleTranslate("en", "ru", postsArray4))
    t4.start();

    t1.join()
    t2.join()
    t3.join()
    t4.join()
    return startTime
  }
}

