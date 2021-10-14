package ru.wref.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "posts")
class Post : BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  override var id: Long? = null;

  @ManyToOne
  @JoinColumn(name = "parent_id", nullable = true)
  var parent: Post? = null;

  @Transient
  var parentId: PostProxy? = null;

  @ManyToOne
  @JoinColumn(name = "accepted_answer_id", nullable = true)
  var acceptedAnswer: Post? = null;

  @Transient
  var acceptedAnswerId: PostProxy? = null;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "posts_tags", joinColumns = [JoinColumn(name = "post_id", referencedColumnName = "post_id")], inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "tag_id")])
  var tagsList: List<Tag>? = null

  @Transient
  var tags: TagProxy? = null

  var postTypeId: Int? = null;

  var creationDate: Date? = null;

  var score: Int? = null;

  var answerCount: Int? = null;

  var commentCount: Int? = null;

  var contentLicense: String? = null;

  var title: String? = null;

  @Column(columnDefinition = "TEXT")
  var body: String? = null;

  @ManyToOne
  @JoinColumn(name = "owner_user_id", nullable = true)
  var ownerUser: User? = null;

  @Transient
  var ownerUserId: UserProxy? = null;

  var lastEditDate: Date? = null;

  var lastActivityDate: Date? = null;

  @ManyToOne
  @JoinColumn(name = "last_editor_user_id", nullable = true)
  var lastEditorUser: User? = null;

  @Transient
  var lastEditorUserId: UserProxy? = null;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  val comments: List<Comment>? = null
}
