package ru.wref.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "tags", uniqueConstraints = [UniqueConstraint(columnNames = ["TagName"])])
@JsonIgnoreProperties(ignoreUnknown = true)
class Tag: BaseModel<Long>() {

  @Id
  @Column(name = "tag_id")
  override var id: Long? = null;

  var tagName: String? = null;

  var excerptPostId: Long? = null;

  var wikiPostId: Long? = null;

  var isModeratorOnly: Boolean? = false;

  var isRequired: Boolean? = false;

  var count: Int? = null;
}
