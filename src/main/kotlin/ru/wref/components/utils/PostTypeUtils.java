package ru.wref.components.utils;

import ru.wref.model.Post;

public class PostTypeUtils {

  public static Post.Type getTypeByString(String type){
    Post.Type typeEn = Post.Type.MOVIE;
    if(type.equals("movies")){
      typeEn =Post.Type.MOVIE;
    }
    if(type.equals("arduino")){
      typeEn =Post.Type.ARDUINO;
    }
    return typeEn;
  }
}
