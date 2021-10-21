package ru.wref.components

import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.TagDTO
import ru.wref.dto.TagList
import ru.wref.model.Tag
import ru.wref.model.User

@Component
class TagComponent: DataComponent<TagDTO,Tag>() {

  fun createTagFromModel(tag: Tag): Tag? {
    try {
      return tagRepository.save(tag);
    }catch (ignore: DataIntegrityViolationException){
      return null;
    }
  }

  fun createTagFromDto(tagDTO: TagDTO): Tag? {
    return createTagFromModel(prepareDtoFrom(tagDTO,Tag::class.java) as Tag)
  }

  /**
   * Create tags from list of received data from xml
   */
  @Transactional
  fun createTagsFromList(tag: TagList): List<Tag> {
    val tags: MutableList<Tag> = mutableListOf()
    for (tag: TagDTO in tag.tagDTOList!!) {
      createTagFromDto(tag)?.let { tags.add(it) };
    }
    return tags;
  }

  fun findLastTag(): Tag? {
    return tagRepository.findTopByOrderByIdDesc()

  }


}
