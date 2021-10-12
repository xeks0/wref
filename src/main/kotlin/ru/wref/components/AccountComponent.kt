package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.AccountDTO
import ru.wref.dto.AccountsList
import ru.wref.model.Account
import ru.wref.repository.AccountRepository
import javax.inject.Inject

@Component
class AccountComponent : DataComponent<AccountDTO,Account>(){

  @Inject
  lateinit var accountRepository: AccountRepository;

  fun createAccountFrom(account: Account): Account {
    return accountRepository.save(account);
  }

  fun createAccountFrom(accountDTO: AccountDTO): Account {
    return createAccountFrom(prepareDtoFrom(accountDTO,Account::class.java) as Account)
  }

  /**
   * Create accounts from list of received data from xml
   */
  @Transactional
  fun createAccountsFromList(account: AccountsList): List<Account> {
    val accounts: MutableList<Account> = mutableListOf()
    for (account: AccountDTO in account.accountDTOList!!) {
      accounts.add(createAccountFrom(account));
    }
    return accounts;
  }


}
