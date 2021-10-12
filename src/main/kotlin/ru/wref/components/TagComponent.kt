package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.TagDTO
import ru.wref.dto.TagList
import ru.wref.model.Tag
import ru.wref.repository.TagRepository
import javax.inject.Inject

@Component
class TagComponent: DataComponent() {

  @Inject
  lateinit var tagRepository: TagRepository;


  fun createTagFromModel(tag: Tag): Tag {
    return tagRepository.save(tag);
  }

  fun createTagFromDto(tagDTO: TagDTO): Tag {
    return createTagFromModel(prepareDtoFrom(tagDTO))
  }

  /**
   * Create tags from list of received data from xml
   */
  @Transactional
  fun createTagsFromList(tag: TagList): List<Tag> {
    val tags: MutableList<Tag> = mutableListOf()
    for (tag: TagDTO in tag.tagDTOList!!) {
      tags.add(createTagFromDto(tag));
    }
    return tags;
  }


}
