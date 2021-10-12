package ru.wref.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.wref.model.Account
import ru.wref.service.MigrationStackExchange
import java.security.Principal
import javax.inject.Inject

@RestController
class AdminController {


  @Inject
  lateinit var migrationStackExchange : MigrationStackExchange
  @GetMapping( "/admin")
  fun admin(principal: Principal): String{
    return "Hello ${principal.name}";
  }

  @GetMapping( "/admin/migration")
  fun adminMigration(principal: Principal): String? {
    return migrationStackExchange.migrationMovie().toString();
  }
}
