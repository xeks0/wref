package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "tags")
@XmlAccessorType(XmlAccessType.FIELD)
class TagList {
  @XmlElement(name = "row")
  val tagDTOList: List<TagDTO>? = null
}
