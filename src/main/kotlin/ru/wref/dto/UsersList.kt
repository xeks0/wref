package ru.wref.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
class UsersList {
  @XmlElement(name = "row")
  var userDTOList: List<UserDTO> = mutableListOf()
}
