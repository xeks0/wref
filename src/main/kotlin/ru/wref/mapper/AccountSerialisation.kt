package ru.wref.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import ru.wref.model.AccountProxy
import ru.wref.repository.AccountRepository
import java.io.IOException

class AccountSerialisation(var accountRepository: AccountRepository) : JsonDeserializer<AccountProxy>(){

  @Throws(IOException::class)
  override fun deserialize(parser: JsonParser, context: DeserializationContext?): AccountProxy? {
    val account = AccountProxy()
    account.account =  accountRepository.findOneById(parser.valueAsLong)
    return account
  }
}
