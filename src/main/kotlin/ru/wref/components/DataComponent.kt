package ru.wref.components


import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import ru.wref.repository.AccountRepository
import ru.wref.repository.TagRepository
import javax.inject.Inject

open class DataComponent<T, W> {

  protected var mapper = jacksonObjectMapper()

  @Inject
  lateinit var tagRepository: TagRepository;

  @Inject
  lateinit var accountRepository: AccountRepository;

  /**
   * Example of use mapper jackson
   * Convert the object to json and back to object model
   */
  fun prepareDtoFrom(data: T, clazz: Class<W>): Any? {
    val javaType: JavaType? = mapper.typeFactory.constructType(clazz);
    val serialized = mapper.writeValueAsString(data);
    return mapper.readValue(serialized, javaType);
  }


}
