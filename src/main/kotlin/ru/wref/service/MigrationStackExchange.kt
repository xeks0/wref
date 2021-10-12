package ru.wref.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.AccountComponent
import ru.wref.components.TagComponent
import ru.wref.dto.AccountsList
import ru.wref.dto.TagList
import javax.inject.Inject
import javax.xml.bind.JAXBContext

@Service
class MigrationStackExchange {

  @Inject
  lateinit var accountComponent: AccountComponent;

  @Inject
  lateinit var tagComponent: TagComponent;

  @Transactional
  fun migrationMovie(): Boolean? {
    return executeAccount() == true && executeTag() == true;
  }

  fun executeAccount():Boolean?{
    val accounts: AccountsList = getResources(JAXBContext.newInstance(AccountsList::class.java),"data/Users.xml") as AccountsList
    return accountComponent.createAccountsFromList(accounts).isNotEmpty();
  }

  fun executeTag():Boolean?{
    val tagList: TagList = getResources(JAXBContext.newInstance(TagList::class.java),"data/Tags.xml") as TagList
    return tagComponent.createTagsFromList(tagList).isNotEmpty();
  }

  private fun getResources(jaxbContext : JAXBContext, file: String): Any? {
    val path = this.javaClass.classLoader.getResource(file);
    val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
    val value = jaxbUnmarshaller.unmarshal(path);
    return value
  }

}
