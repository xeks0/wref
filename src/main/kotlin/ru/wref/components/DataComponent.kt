package ru.wref.components


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


open class DataComponent<T, W> {

  val mapper = jacksonObjectMapper()

  /**
   * Example of use mapper jackson
   * Convert the object to json and back to object model
   */
  fun prepareDtoFrom(data: T, clazz: Class<W>): Any? {
    val javaType: com.fasterxml.jackson.databind.JavaType? = mapper.getTypeFactory().constructType(clazz);
    val serialized = mapper.writeValueAsString(data);
    return mapper.readValue(serialized, javaType);
  }

}
