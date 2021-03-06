package ru.wref.components

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.stereotype.Component

@Component
class ModuleRegistration<T> {

  fun registerModule(java: Class<T>, serialisation: JsonDeserializer<T>, mapper: ObjectMapper): ObjectMapper {
    val accountModule = SimpleModule()
    accountModule.addDeserializer(java, serialisation)
    mapper.registerModule(accountModule)
    return mapper;
  }



}
