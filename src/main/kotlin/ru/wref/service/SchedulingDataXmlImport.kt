package ru.wref.service

import com.redfin.sitemapgenerator.W3CDateFormat
import com.redfin.sitemapgenerator.WebSitemapGenerator
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.wref.model.Post
import java.io.File
import java.util.*
import javax.inject.Inject


@EnableScheduling
@Service
open class SchedulingDataXmlImport {

  @Inject
  lateinit var migrationProductionDataXML: MigrationProductionDataXML;

  @Scheduled(fixedRateString = "\${timing.updateData}", initialDelayString = "\${timing.initialDelay}")
  open fun importDataXMLTask(){
//    println("SchedulingDataXmlImport.importDataXMLTask")
//    migrationProductionDataXML.exportData("data/arduino", 2000, 300000,Post.Type.ARDUINO)
  }



  @Scheduled(fixedRateString = "\${timing.updateDataTranslate}", initialDelayString = "\${timing.initialDelay}")
  open fun translateData(){
    println("SchedulingDataXmlImport.translateData")
    for (i in 1..5000000) {
      migrationProductionDataXML.translate(100,Post.Type.ARDUINO)
      if (i % 100 == 0) {
        Thread.sleep(10000)
      }
      Thread.sleep(5000)
    }
  }
  @Scheduled(fixedRateString = "\${timing.updateDataTranslate}", initialDelayString = "\${timing.initialDelay}")
  open fun generateSitemap(){
//    var postList:List<Post> = migrationProductionDataXML.postComponent.findAllByPostTypeAndType(1,Post.Type.MOVIE);
//    val dateFormat = W3CDateFormat(W3CDateFormat.Pattern.DAY)
//    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
//    val wsg = WebSitemapGenerator.builder("https://wref.ru", File("/home/xeks/__WREF__/frontend/wref/static"))
//      .dateFormat(dateFormat).build() // actually use the configured dateFormat
//    for (post in postList){
//      wsg.addUrl("https://wref.ru/"+post.type.name.lowercase()+"/questions/"+post.id)
//    }
//    for (i in 1..2118){
//      wsg.addUrl("https://wref.ru/movies/questions/page/"+i.toString())
//    }
//    wsg.addUrl("https://wref.ru/movies")
//
//    wsg.write()
  }
  @Scheduled(fixedRateString = "\${timing.updateDataTranslate}", initialDelayString = "\${timing.initialDelay}")
  open fun changeDataTranslate(){
//    migrationProductionDataXML.changeDataTranslate()
  }

  @Scheduled(fixedRateString = "\${timing.updateDataTranslate}", initialDelayString = "\${timing.initialDelay}")
  open fun translateDataFix(){
//    println("SchedulingDataXmlImport.translateData")
//    migrationProductionDataXML.fixTranslate()
  }
}
