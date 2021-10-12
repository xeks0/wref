package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ru.wref.dto.AccountDTO
import ru.wref.dto.TagDTO
import ru.wref.model.Account
import ru.wref.model.Tag

open class DataComponent {

  val mapper = jacksonObjectMapper()

  fun prepareFromDto(account: Account): AccountDTO {
    val serialized = mapper.writeValueAsString(account);
    return  mapper.readValue(serialized);
  }
  /**
   * Example of use mapper jackson
   * Convert the object AccountDTO to json and back to object model Account
   */
  fun prepareDtoFrom(accountDTO: AccountDTO): Account {
    val serialized = mapper.writeValueAsString(accountDTO);
    return  mapper.readValue(serialized);
  }

  fun prepareFromDto(tag: Tag): TagDTO {
    val serialized = mapper.writeValueAsString(tag);
    return  mapper.readValue(serialized);
  }
  /**
   * Example of use mapper jackson
   * Convert the object TagDTO to json and back to object model Tag
   */
  fun prepareDtoFrom(tag: TagDTO): Tag {
    val serialized = mapper.writeValueAsString(tag);
    return  mapper.readValue(serialized);
  }
}
