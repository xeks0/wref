package ru.wref.components

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import ru.wref.dto.AccountDTO
import ru.wref.model.Account

@Component
class AccountDataAndFilter {

  val mapper = jacksonObjectMapper()

  fun prepareAccountFromDto(account: Account): AccountDTO{
    val serialized = mapper.writeValueAsString(account);
    return  mapper.readValue(serialized);
  }

  fun prepareDtoFromAccount(accountDTO: AccountDTO): Account{
    val serialized = mapper.writeValueAsString(accountDTO);
    return  mapper.readValue(serialized);
  }
}
