package ru.wref.components

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.TagDTO
import ru.wref.dto.TagList
import ru.wref.model.Tag

@Component
class TagComponent: DataComponent<TagDTO,Tag>() {

  fun createTagFromModel(tag: Tag, startIds: Int): Tag? {
    try {
      tag.id = tag.id?.plus(startIds);
      if(tagRepository.findOneByTagName(tag.tagName.toString()) ==null){
        return tagRepository.save(tag);
      }
    return tagRepository.findOneByTagName(tag.tagName.toString());
    }catch (ignore: DataIntegrityViolationException){
      return null;
    }
  }

  fun createTagFromDto(tagDTO: TagDTO,startIds: Int): Tag? {
    return createTagFromModel(prepareDtoFrom(tagDTO,Tag::class.java) as Tag,startIds)
  }

  /**
   * Create tags from list of received data from xml
   */
  @Transactional
  fun createTagsFromList(tag: TagList, startIds: Int): List<Tag> {
    val tags: MutableList<Tag> = mutableListOf()
    for (tag: TagDTO in tag.tagDTOList!!) {
      createTagFromDto(tag,startIds)?.let { tags.add(it) };
    }
    return tags;
  }

  fun findLastTag(): Tag? {
    return tagRepository.findTopByOrderByIdDesc()

  }


}
