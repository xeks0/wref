package ru.wref.service

import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.wref.repository.AccountRepository
import javax.inject.Inject

@FlywayTest(locationsForMigrate = ["db/migration"])
@SpringBootTest
class MigrationStackExchangeTest {

  @Inject
  lateinit var migrationStackExchange: MigrationStackExchange;

  @Inject
  lateinit var accountRepository: AccountRepository;

  @Test
  fun testCreateAccount() {
    migrationStackExchange.migrationMovie()
    assert(accountRepository.findAll().size > 0)
  }
}
