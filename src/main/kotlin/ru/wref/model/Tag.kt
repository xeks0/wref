package ru.wref.model

import javax.persistence.*

@Entity
@Table(name = "tags", uniqueConstraints = [UniqueConstraint(columnNames = ["TagName"])])
class Tag: BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tag_id")
  override var id: Long? = null;

  var tagName: String? = null;

  var excerptPostId: Long? = null;

  var wikiPostId: Long? = null;

  var isModeratorOnly: Boolean? = false;

  var isRequired: Boolean? = false;

  var count: Int? = null;
}
