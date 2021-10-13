package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
class PostList {
  @XmlElement(name = "row")
  val postDTOList: List<PostDTO>? = null
}
