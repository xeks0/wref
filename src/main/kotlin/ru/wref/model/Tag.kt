package ru.wref.model

import javax.persistence.*

@Entity
@Table(name = "tags", uniqueConstraints = [UniqueConstraint(columnNames = ["TagName"])])
class Tag: BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  override var id: Long? = null;

  var TagName: String? = null;

  var ExcerptPostId: Long? = null;

  var WikiPostId: Long? = null;

  var IsModeratorOnly: Boolean? = false;

  var IsRequired: Boolean? = false;

  var Count: Int? = null;
}
