package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
@XmlAccessorType(XmlAccessType.FIELD)
class TagDTO {

  @XmlAttribute(name = "Id")
  var id: String? = null;

  @XmlAttribute(name = "TagName")
  var TagName: String? = null;

  @XmlAttribute(name = "ExcerptPostId")
  var ExcerptPostId: Long? = null;

  @XmlAttribute(name = "WikiPostId")
  var WikiPostId: Long? = null;

  @XmlAttribute(name = "IsModeratorOnly")
  var IsModeratorOnly: Boolean? = false;

  @XmlAttribute(name = "IsRequired")
  var IsRequired: Boolean? = false;

  @XmlAttribute(name = "Count")
  var Count: Int? = null;
}
