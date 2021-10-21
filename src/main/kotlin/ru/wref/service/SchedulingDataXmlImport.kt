package ru.wref.service

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.inject.Inject


@EnableScheduling
@Service
open class SchedulingDataXmlImport {

  @Inject
  lateinit var migrationProductionDataXML: MigrationProductionDataXML;

  @Scheduled(fixedDelay = 1000)
  open fun importDataXMLTask(){
    println("SchedulingDataXmlImport.importDataXMLTask")
    migrationProductionDataXML.exportData("data",1000)
  }
}
