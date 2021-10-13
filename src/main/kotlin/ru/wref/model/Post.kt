package ru.wref.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.wref.dto.TagList
import ru.wref.mapper.AccountSerialisation
import ru.wref.mapper.PostSerialisation
import ru.wref.mapper.TagSerialisation
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

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
  var ownerUser: Account? = null;

  @Transient
  var ownerUserId: AccountProxy? = null;

  var lastEditDate: Date? = null;

  var lastActivityDate: Date? = null;

  @ManyToOne
  @JoinColumn(name = "last_editor_user_id", nullable = true)
  var lastEditorUser: Account? = null;

  @Transient
  var lastEditorUserId: AccountProxy? = null;
}
