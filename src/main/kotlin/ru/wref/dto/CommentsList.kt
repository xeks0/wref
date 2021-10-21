package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "comments")
@XmlAccessorType(XmlAccessType.FIELD)
class CommentsList {
  @XmlElement(name = "row")
  var commentDTOList: List<CommentDTO> = mutableListOf()
}
