package ru.wref.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.data.repository.findByIdOrNull
import ru.wref.model.UserProxy
import ru.wref.repository.UserRepository
import java.io.IOException

class UserSerialisation(var userRepository: UserRepository, var startIds: Int) : JsonDeserializer<UserProxy>(){

  @Throws(IOException::class)
  override fun deserialize(parser: JsonParser, context: DeserializationContext?): UserProxy? {
    val account = UserProxy()
    val id:Long = parser.valueAsLong.plus(startIds);
    account.user =  userRepository.findByIdOrNull(id)
    return account
  }
}
