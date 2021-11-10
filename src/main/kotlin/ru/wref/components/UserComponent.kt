package ru.wref.components

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.components.utils.OffsetBasedPageRequest
import ru.wref.dto.UserDTO
import ru.wref.dto.UsersList
import ru.wref.model.User

@Component
class UserComponent : DataComponent<UserDTO,User>(){

  fun createAccountFrom(user: User,startIds: Int): User {
      user.id = user.id?.plus(startIds);
      return userRepository.save(user);
  }
  fun createUserForce(user: User): User {
    return userRepository.save(user);
  }
  fun createAccountFrom(userDTO: UserDTO,startIds: Int): User {
    return createAccountFrom(prepareDtoFrom(userDTO,User::class.java) as User,startIds)
  }

  /**
   * Create accounts from list of received data from xml
   */
  @Transactional
  fun createUserFromList(account: UsersList, startIds: Int): List<User> {
    val users: MutableList<User> = mutableListOf()
    for (user: UserDTO in account.userDTOList!!) {
      users.add(createAccountFrom(user,startIds));
    }
    return users;
  }

  fun findLastUser(): User? {
    return userRepository.findTopByOrderByIdDesc()
  }

  fun getLastUserTranslate(): User? {
    return userRepository.findTopByOrderByIsTranslateAsc()
  }

  fun getUserFromId(id: Long): User? {
    return userRepository.getOneById(id);
  }

  fun getPostFromStartAndLimit(start: User, end: Int): Page<User> {
    val pageable: Pageable = OffsetBasedPageRequest(start.id!!.toInt(), end)
    return userRepository.findAll(pageable)
  }




}
