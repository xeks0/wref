package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.AccountDTO
import ru.wref.dto.AccountsList
import ru.wref.model.Account
import ru.wref.repository.AccountRepository
import javax.inject.Inject

@Component
class AccountComponent {

  @Inject
  lateinit var accountRepository: AccountRepository;

  @Inject
  lateinit var accountDataAndFilter: AccountDataAndFilter;

  fun createAccountFromModel(account: Account): Account {
    return accountRepository.save(account);
  }

  fun createAccountFromDto(accountDTO: AccountDTO): Account {
    return createAccountFromModel(accountDataAndFilter.prepareDtoFromAccount(accountDTO))
  }

  @Transactional
  fun createAccountFromList(account: AccountsList): List<Account> {
    val accounts: MutableList<Account> = mutableListOf()
    for (account: AccountDTO in account.accountDTOList!!) {
      accounts.add(createAccountFromDto(account));
    }
    return accounts;
  }


}
