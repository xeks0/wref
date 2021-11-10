package ru.wref.service;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import lombok.SneakyThrows;
import ru.wref.model.Post;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class GoogleTranslatePost implements Runnable {
  String langFrom;
  String langTo;
  List<Post> postList;
  Post.Type type;

  public GoogleTranslatePost(String langFrom, String langTo, List<Post> postList, Post.Type type) {
    this.langFrom = langFrom;
    this.langTo = langTo;
    this.postList = postList;
    this.type = type;
  }

  public void translate() throws IOException, InterruptedException {
    ChromeDriverManager.getInstance(DriverManagerType.CHROME).browserVersion("94.0.4606.61").setup();
    Configuration.startMaximized = true;


    Thread.sleep(1000);
    for (int i = 0; i < postList.size(); i++) {

      Thread.sleep(200);
      Post post = postList.get(i);
      open("http://localhost:3000/"+type.name().toLowerCase()+"/questions/"+post.getId());
      Thread.sleep(300);
      int countScrolle = 5;

      XdotScript testScript = new XdotScript();
      testScript.runScript("/bin/sh /home/xeks/xdotool_script");

      if($x("//div[@id='clientHeight']").exists()) {
        countScrolle = Integer.parseInt($x("//div[@id='clientHeight']").getText());
      }

      if(countScrolle>1080){
        countScrolle = countScrolle/100;
        for (int j = 0; j < countScrolle; j++) {
          testScript = new XdotScript();
          testScript.runScript("/bin/sh /home/xeks/xdotool_script_scroll_down");
        }
        for (int j = 0; j < countScrolle; j++) {
          testScript = new XdotScript();
          testScript.runScript("/bin/sh /home/xeks/xdotool_script_scroll_up");
        }
        testScript = new XdotScript();
        testScript.runScript("/bin/sh /home/xeks/xdotool_script_scroll_up");
      }

      post.setTranslate(1);
      Thread.sleep(2000);
      int w= 0;
      while (true) {
        if ($x("//button[@id='save_translate']").exists() && $x("//button[@id='save_translate']").getText().equalsIgnoreCase("Сохранить")) {
          $x("//button[@id='save_translate']").click();
          break;
        }
         testScript = new XdotScript();
        testScript.runScript("/bin/sh /home/xeks/xdotool_script");
        Thread.sleep(2000);
        w++;
        if (w > 10) {
          break;
        }
      }
    }

  }

  @SneakyThrows
  @Override
  public void run() {
    translate();
  }
}
