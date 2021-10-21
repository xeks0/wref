package ru.wref.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User : BaseModel<Long>() {

  @Id
  @Column(name = "user_id")
  override var id: Long? = null;

  var displayName: String? = null;

  var lastAccessDate: Date? = null;

  var websiteUrl: String? = null;

  var location: String? = null;

  @Column(columnDefinition = "TEXT")
  var aboutMe: String? = null;

  var views: Int? = null;

  var upVotes: Int? = null;

  var downVotes: Int? = null;


}
