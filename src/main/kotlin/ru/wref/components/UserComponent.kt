package ru.wref.components

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.wref.dto.UserDTO
import ru.wref.dto.UsersList
import ru.wref.model.User

@Component
class UserComponent : DataComponent<UserDTO,User>(){

  fun createAccountFrom(user: User): User {
    return userRepository.save(user);
  }

  fun createAccountFrom(userDTO: UserDTO): User {
    return createAccountFrom(prepareDtoFrom(userDTO,User::class.java) as User)
  }

  /**
   * Create accounts from list of received data from xml
   */
  @Transactional
  fun createUserFromList(account: UsersList): List<User> {
    val users: MutableList<User> = mutableListOf()
    for (user: UserDTO in account.userDTOList!!) {
      users.add(createAccountFrom(user));
    }
    return users;
  }


}
