package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.TagDTO
import ru.wref.dto.TagList
import ru.wref.model.Tag
import ru.wref.repository.TagRepository
import javax.inject.Inject

@Component
class TagComponent {

  @Inject
  lateinit var tagRepository: TagRepository;

  @Inject
  lateinit var tagDataAndFilter: TagDataAndFilter;

  fun createTagFromModel(tag: Tag): Tag {
    return tagRepository.save(tag);
  }

  fun createTagFromDto(tagDTO: TagDTO): Tag {
    return createTagFromModel(tagDataAndFilter.prepareDtoFromTag(tagDTO))
  }

  @Transactional
  fun createTagsFromList(tag: TagList): List<Tag> {
    val tags: MutableList<Tag> = mutableListOf()
    for (tag: TagDTO in tag.tagDTOList!!) {
      tags.add(createTagFromDto(tag));
    }
    return tags;
  }


}
