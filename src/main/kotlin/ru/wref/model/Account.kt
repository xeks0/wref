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
  @Getter
  @Setter
  val id: Long? = null;
  @Getter
  @Setter
  val DisplayName: String? = null;

  @Getter
  @Setter
  val LastAccessDate: Date? = null;

  @Getter
  @Setter
  val WebsiteUrl: String? = null;

  @Getter
  @Setter
  val Location: String? = null;

  @Getter
  @Setter
  val AboutMe: String? = null;

  @Getter
  @Setter
  val Views: Integer? = null;

  @Getter
  @Setter
  val UpVotes: Integer? = null;

  @Getter
  @Setter
  val DownVotes: Integer? = null;

  @Getter
  @Setter
  val AccountId: Integer? = null;
}
