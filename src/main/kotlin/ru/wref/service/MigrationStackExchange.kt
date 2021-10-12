package ru.wref.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.AccountComponent
import ru.wref.dto.AccountsList
import java.io.File
import javax.inject.Inject
import javax.xml.bind.JAXBContext


@Service
class MigrationStackExchange {

  @Inject
  lateinit var accountComponent: AccountComponent;

  @Transactional
  fun migrationMovie(): Boolean? {
    val path = this.javaClass.classLoader.getResource("data/Users.xml");
    val jaxbContext: JAXBContext = JAXBContext.newInstance(AccountsList::class.java)
    val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
    val accounts: AccountsList = jaxbUnmarshaller.unmarshal(path) as AccountsList;
    return accountComponent.createAccountFromList(accounts).isNotEmpty();
  }


}
