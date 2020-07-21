package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.User
import com.fon.knjizararest.repository.UserRepository
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/users")
class UserRestController(@Autowired val userRepository: UserRepository) {

    @GetMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserByUsername(@PathVariable username: String): ResponseEntity<User> {
        val user = userRepository.findUserByUsername(username)
        return when (user.isPresent) {
            true -> ResponseEntity(user.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUserByUsername(@PathVariable username: String): ResponseEntity<Any> {
        return when (userRepository.findUserByUsername(username).isPresent) {
            true -> {
                userRepository.deleteUserByUsername(username)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveUser(@RequestBody user: User): ResponseEntity<Any> {
        return when (userRepository.findUserByUsername(user.username).isPresent) {
            false -> {
                userRepository.save(user)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUser(@RequestBody user: User): ResponseEntity<Any> {
        return when (userRepository.findUserByUsername(user.username).isPresent) {
            true -> {
                userRepository.save(user)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}