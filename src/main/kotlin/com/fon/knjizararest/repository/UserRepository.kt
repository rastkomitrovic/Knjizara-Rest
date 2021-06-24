package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserByUsername(username: String): Optional<User>
}