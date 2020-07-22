package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.User
import com.fon.knjizararest.repository.UserRepository
import com.fon.knjizararest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(@Autowired val userRepository: UserRepository) : UserService {
    override fun findUserByUsername(username: String): Optional<User> {
        return userRepository.findUserByUsername(username)
    }

    override fun deleteUserByUsername(username: String) {
        return userRepository.deleteUserByUsername(username);
    }

    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    override fun findUserByUserId(userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }

}