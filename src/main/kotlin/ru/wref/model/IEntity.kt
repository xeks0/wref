package ru.wref.model

import java.io.Serializable

internal interface IEntity<T : Serializable?> : Serializable {
  var id: T
}

