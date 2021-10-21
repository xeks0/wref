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


public class GoogleTranslate implements Runnable{
  String langFrom;
  String langTo;
  List<Post> postList;

  public GoogleTranslate(String langFrom, String langTo, List<Post> postList) {
    this.langFrom = langFrom;
    this.langTo = langTo;
    this.postList = postList;
  }

  public void translate() throws IOException, InterruptedException {
    ChromeDriverManager.getInstance(DriverManagerType.CHROME).browserVersion("94.0.4606.61").setup();
    Configuration.startMaximized = true;

    for (int i = 0; i < postList.size(); i++) {
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");

      Post post = postList.get(i);
      if(post.getTitle()!=null){
        $x("//textarea[@class='er8xn']").clear();
        $x("//textarea[@class='er8xn']").sendKeys(post.getTitle());
        post.setTitleRu($x("//span[@class='VIiyi']").getText());
        Thread.sleep(200);
      }
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");

      if(post.getBody()!=null) {
        $x("//textarea[@class='er8xn']").clear();
        $x("//textarea[@class='er8xn']").sendKeys(post.getBody());
        post.setBodyRu($x("//span[@class='VIiyi']").getText());
        Thread.sleep(200);
      }
    }

  }

  @SneakyThrows
  @Override
  public void run() {
    translate();
  }
}
