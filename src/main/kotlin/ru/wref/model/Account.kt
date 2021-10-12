package ru.wref.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "accounts", uniqueConstraints = [UniqueConstraint(columnNames = ["AccountId"])])
class Account : BaseModel<Long>() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  override var id: Long? = null;

  var DisplayName: String? = null;

  var LastAccessDate: Date? = null;

  var WebsiteUrl: String? = null;

  var Location: String? = null;

  @Column(columnDefinition = "TEXT")
  var AboutMe: String? = null;

  var Views: Int? = null;

  var UpVotes: Int? = null;

  var DownVotes: Int? = null;

  var AccountId: Int? = null;
}
