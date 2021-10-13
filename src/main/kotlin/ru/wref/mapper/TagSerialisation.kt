package ru.wref.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.stereotype.Component
import ru.wref.model.Tag
import ru.wref.model.TagProxy
import ru.wref.repository.TagRepository
import java.io.IOException
import java.util.*

@Component
class TagSerialisation(var tagRepository: TagRepository) : JsonDeserializer<TagProxy>() {

  @Throws(IOException::class)
  override fun deserialize(parser: JsonParser, context: DeserializationContext): TagProxy {
    val tagsString = parser.valueAsString
      .replace("<", " ")
      .replace(">", " ")
      .trim { it <= ' ' }.split(" ").toTypedArray()
    val tags: MutableList<Tag> = ArrayList()
    for (tagString in tagsString) {
      if (tagString.length < 4) {
        continue
      }
      val tag = tagRepository.findOneByTagName(tagString)
      if (tag != null)
        tags.add(tag)
    }
    val t = TagProxy()
    t.tags = tags;
    return t;
  }
}
