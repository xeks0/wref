package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import ru.wref.dto.TagDTO
import ru.wref.model.Tag

@Component
class TagDataAndFilter {

  val mapper = jacksonObjectMapper()

  fun prepareTagFromDto(tag: Tag): TagDTO{
    val serialized = mapper.writeValueAsString(tag);
    return  mapper.readValue(serialized);
  }

  fun prepareDtoFromTag(tag: TagDTO): Tag{
    val serialized = mapper.writeValueAsString(tag);
    return  mapper.readValue(serialized);
  }
}
