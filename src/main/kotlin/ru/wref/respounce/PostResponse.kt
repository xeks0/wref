package ru.wref.respounce

import ru.wref.model.Post

class PostResponse(var post: Post?, var child:List<Post>, var left:Post?, var right: Post? ) {
}
