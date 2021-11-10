package ru.wref.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import ru.wref.model.PostProxy
import ru.wref.repository.PostRepository
import java.io.IOException

class PostSerialisation(val postRepository: PostRepository, var startIds: Int) : JsonDeserializer<PostProxy>() {

  @Throws(IOException::class)
  override fun deserialize(parser: JsonParser, context: DeserializationContext): PostProxy? {
    val post = PostProxy()
    val id:Long  = parser.valueAsLong.plus(startIds);
    post.post = postRepository.getOneById(id)
    return post
  }
}
