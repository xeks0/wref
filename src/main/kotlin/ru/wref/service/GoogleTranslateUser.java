package ru.wref.service;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import lombok.SneakyThrows;
import ru.wref.model.Comment;
import ru.wref.model.User;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class GoogleTranslateUser implements Runnable {
  String langFrom;
  String langTo;
  List<User> userList;

  public GoogleTranslateUser(String langFrom, String langTo, List<User> userList) {
    this.langFrom = langFrom;
    this.langTo = langTo;
    this.userList = userList;
  }

  public void translate() throws IOException, InterruptedException {
    ChromeDriverManager.getInstance(DriverManagerType.CHROME).browserVersion("94.0.4606.61").setup();
    Configuration.startMaximized = true;

    open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
    if($x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").exists()) {
      $x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").click();
    }
    Thread.sleep(1000);
    for (int i = 0; i < userList.size(); i++) {
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
      Thread.sleep(200);
      User post = userList.get(i);
      if (post.getAboutMe() != null) {
        try {
          $x("//textarea[@class='er8xn']").clear();
          $x("//textarea[@class='er8xn']").sendKeys(post.getAboutMe());
          Thread.sleep(1000);
          post.setAboutMeRu($x("//div[@class='J0lOec']").getText());
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
