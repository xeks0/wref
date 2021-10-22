package ru.wref.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import kotlin.jvm.Transient

@XmlAccessorType(XmlAccessType.FIELD)
class CommentDTO {

  @XmlAttribute(name = "Id")
  var id: String? = null;

  @XmlAttribute(name = "PostId")
  var PostId: String? = null;

  @XmlAttribute(name = "Score")
  var Score: String? = null;

  @XmlAttribute(name = "Text")
  var Text: String? = null;

  @XmlAttribute(name = "UserId")
  var UserId: String? = null;

  @XmlAttribute(name = "UserDisplayName")
  var UserDisplayName: String? = null;

  @XmlAttribute(name = "CreationDate")
  var CreationDate: String? = null;

  @XmlAttribute(name = "ContentLicense")
  var ContentLicense: String? = null;
}
