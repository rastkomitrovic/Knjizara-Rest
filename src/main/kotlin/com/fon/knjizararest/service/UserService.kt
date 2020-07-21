package com.fon.knjizararest.service

import com.fon.knjizararest.entity.User
import java.util.*

interface UserService {
    fun saveUser(user: User) //default method
    fun findUserByUsername(username: String): Optional<User>
    fun deleteUserByUsername(username: String)
}