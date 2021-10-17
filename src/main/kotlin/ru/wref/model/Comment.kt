package ru.wref.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "comments")
@JsonIgnoreProperties(ignoreUnknown = true)
class Comment : BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  override var id: Long? = null;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @JsonIgnore
  var post: Post? = null;

  @Transient
  var postId: PostProxy? = null;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = true)
  var user: User? = null;

  @Transient
  var UserId: UserProxy? = null;

  var score: Int? = null;

  @Column(columnDefinition = "TEXT")
  var text: String? = null;

  var userDisplayName: String? = null;

  var creationDate: Date? = null;

  var contentLicense: String? = null;
}
