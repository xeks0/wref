package ru.wref.service;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.wref.model.Post;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class GoogleTranslatePost implements Runnable {
  String langFrom;
  String langTo;
  List<Post> postList;

  public GoogleTranslatePost(String langFrom, String langTo, List<Post> postList) {
    this.langFrom = langFrom;
    this.langTo = langTo;
    this.postList = postList;
  }

  public void translate() throws IOException, InterruptedException {
    ChromeDriverManager.getInstance(DriverManagerType.CHROME).browserVersion("94.0.4606.61").setup();
    Configuration.startMaximized = true;

    open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
    if($x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").exists()) {
      $x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").click();
    }

    Thread.sleep(1000);
    for (int i = 0; i < postList.size(); i++) {
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
      Thread.sleep(200);
      Post post = postList.get(i);
      if (post.getTitle() != null) {
        try {
          $x("//textarea[@class='er8xn']").clear();
          $x("//textarea[@class='er8xn']").sendKeys(post.getTitle());
          Thread.sleep(1000);
          post.setTitleRu($x("//div[@class='J0lOec']").getText());

          post.setTranslate(2);
        } catch (Exception er) {
          post.setTranslate(-1);
        }

      }
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
      Thread.sleep(200);
      if (post.getBody() != null) {
        try {
          $x("//textarea[@class='er8xn']").clear();
          $x("//textarea[@class='er8xn']").sendKeys(post.getBody());
          Thread.sleep(1000);
          post.setBodyRu($x("//div[@class='J0lOec']").getText());
          post.setTranslate(1);
        } catch (Exception er) {
          post.setTranslate(-1);
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
