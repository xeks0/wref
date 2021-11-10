package ru.wref.service;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import lombok.SneakyThrows;
import ru.wref.model.Comment;
import ru.wref.model.Post;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class GoogleTranslateComment implements Runnable {
  String langFrom;
  String langTo;
  Set<Comment> commentList;

  public GoogleTranslateComment(String langFrom, String langTo, Set<Comment> commentList) {
    this.langFrom = langFrom;
    this.langTo = langTo;
    this.commentList = commentList;
  }

  public void translate() throws IOException, InterruptedException {
    ChromeDriverManager.getInstance(DriverManagerType.CHROME).browserVersion("94.0.4606.61").setup();
    Configuration.startMaximized = true;

    open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
    if($x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").exists()) {
      $x("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc']").click();
    }
    Thread.sleep(1000);
    for (int i = 0; i < commentList.size(); i++) {
      open("https://translate.google.com/?hl=ru#view=home&op=translate&sl=en&tl=ru");
      Thread.sleep(200);
      Comment post = (Comment) commentList.stream().toArray()[i];
      post.setTranslate(1);
      if (post.getText() != null) {
        try {
          $x("//textarea[@class='er8xn']").clear();
          $x("//textarea[@class='er8xn']").sendKeys(post.getText());
          int w= 0;
          while (true) {
            if ($x("//div[@class='J0lOec']").exists()) {
              post.setTextRu($x("//div[@class='J0lOec']").getText());
              break;
            }
            Thread.sleep(2000);
            w++;
            if (w == 10) {
              post.setTranslate(-1);
              break;
            }
          }
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
