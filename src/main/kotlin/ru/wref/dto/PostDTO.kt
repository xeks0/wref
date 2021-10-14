package ru.wref.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute

@XmlAccessorType(XmlAccessType.PROPERTY)
class PostDTO  {

  @XmlAttribute(name = "Id")
  var Id: String? = null;

  @XmlAttribute(name = "ParentId")
  @JsonIgnore
  var ParentId: String? = null;

  @XmlAttribute(name = "AcceptedAnswerId")
  @JsonIgnore
  var AcceptedAnswerId: String? = null;

  @XmlAttribute(name = "Tags")
  @JsonIgnore
  var Tags : String?  = null

  @XmlAttribute(name = "PostTypeId")
  var PostTypeId: String? = null;

  @XmlAttribute(name = "CreationDate")
  var CreationDate: String? = null;

  @XmlAttribute(name = "Score")
  var Score: String? = null;

  @XmlAttribute(name = "AnswerCount")
  var AnswerCount: String? = null;

  @XmlAttribute(name = "CommentCount")
  var CommentCount: String? = null;

  @XmlAttribute(name = "ContentLicense")
  var ContentLicense:String? = null;

  @XmlAttribute(name = "Title")
  var Title:String? = null;

  @XmlAttribute(name = "Body")
  var Body: String? = null;

  @XmlAttribute(name = "OwnerUserId")
  @JsonIgnore
  var OwnerUserId: String? = null;

  @XmlAttribute(name = "LastEditDate")
  var LastEditDate: String? = null;

  @XmlAttribute(name = "LastActivityDate")
  var LastActivityDate: String? = null;

  @XmlAttribute(name = "LastEditorUserId")
  @JsonIgnore
  var LastEditorUserId: String? = null;
}
