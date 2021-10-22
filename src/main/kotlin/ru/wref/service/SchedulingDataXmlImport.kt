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

  @Scheduled(fixedRateString = "\${timing.updateData}", initialDelayString = "\${timing.initialDelay}")
  open fun importDataXMLTask(){
    println("SchedulingDataXmlImport.importDataXMLTask")
    migrationProductionDataXML.exportData("data_meta",1000)
  }
}
