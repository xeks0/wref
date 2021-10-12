package ru.wref.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "accounts")
class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  val id: Long? = null;


  var DisplayName: String? = null;

  var LastAccessDate: Date? = null;

  var WebsiteUrl: String? = null;

  var Location: String? = null;

  var AboutMe: String? = null;

  var Views: Integer? = null;

  var UpVotes: Integer? = null;

  var DownVotes: Integer? = null;

  var AccountId: Integer? = null;
}
