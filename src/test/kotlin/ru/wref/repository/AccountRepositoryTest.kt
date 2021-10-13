package ru.wref.repository

import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.wref.model.Account
import javax.inject.Inject

@FlywayTest(locationsForMigrate = ["db/migration"])
@SpringBootTest
//@AutoConfigureEmbeddedDatabase
class AccountRepositoryTest {

  @Inject
  lateinit var accountRepository: AccountRepository;

  @Test
  fun testCreateAccount() {
    val account: Account = Account();
    account.displayName = "New Name"
    accountRepository.save(account)
    assert(accountRepository.findAll().size > 0)
  }
}
