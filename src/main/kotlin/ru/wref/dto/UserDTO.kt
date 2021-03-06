package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement


@XmlAccessorType(XmlAccessType.FIELD)
class UserDTO {

  @XmlAttribute(name = "Id")
  var id: String? = null;

  @XmlAttribute(name = "DisplayName")
  var DisplayName: String? = null;

  @XmlAttribute(name = "LastAccessDate")
  var LastAccessDate: String? = null;

  @XmlAttribute(name = "WebsiteUrl")
  var WebsiteUrl: String? = null;

  @XmlAttribute(name = "Location")
  var Location: String? = null;

  @XmlAttribute(name = "AboutMe")
  var AboutMe: String? = null;

  @XmlAttribute(name = "Views")
  var Views: String? = null;

  @XmlAttribute(name = "UpVotes")
  var UpVotes: String? = null;

  @XmlAttribute(name = "DownVotes")
  var DownVotes: String? = null;

}
