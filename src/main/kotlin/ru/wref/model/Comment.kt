package ru.wref.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment : BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  override var id: Long? = null;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
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
