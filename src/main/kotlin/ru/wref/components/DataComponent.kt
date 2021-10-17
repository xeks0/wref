package ru.wref.components


import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import ru.wref.model.CommentProxy
import ru.wref.model.PostProxy
import ru.wref.model.TagProxy
import ru.wref.model.UserProxy
import ru.wref.repository.CommentRepository
import ru.wref.repository.PostRepository
import ru.wref.repository.UserRepository
import ru.wref.repository.TagRepository
import javax.inject.Inject

open class DataComponent<T, W> {

  protected var mapper = jacksonObjectMapper()

  @Inject
  lateinit var postRepository: PostRepository;

  @Inject
  lateinit var tagRepository: TagRepository;

  @Inject
  lateinit var userRepository: UserRepository;

  @Inject
  lateinit var commentRepository: CommentRepository;

  @Inject
  lateinit var moduleRegistrationUser: ModuleRegistration<UserProxy>;

  @Inject
  lateinit var moduleRegistrationTag: ModuleRegistration<TagProxy>;

  @Inject
  lateinit var moduleRegistrationPost: ModuleRegistration<PostProxy>;

  @Inject
  lateinit var moduleRegistrationComment: ModuleRegistration<CommentProxy>;


  /**
   * Example of use mapper jackson
   * Convert the object to json and back to object model
   */
  fun prepareDtoFrom(data: T, clazz: Class<W>): Any? {
    val javaType: JavaType? = mapper.typeFactory.constructType(clazz);
    val serialized = mapper.writeValueAsString(data);
    return mapper.readValue(serialized, javaType);
  }
  fun prepareEntityToDto(data: W, clazz: Class<T>): Any? {
    val javaType: JavaType? = mapper.typeFactory.constructType(clazz);
    val serialized = mapper.writeValueAsString(data);
    return mapper.readValue(serialized, javaType);
  }

}
