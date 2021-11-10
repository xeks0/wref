package ru.wref.respounce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostUpdate implements Serializable {

  private Long id;
  private String title;
  private String body;

  private List<PostUpdate> child = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<PostUpdate> getChild() {
    return child;
  }

  public void setChild(List<PostUpdate> child) {
    this.child = child;
  }
}
