package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.User
import com.fon.knjizararest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/users")
class UserRestController(@Autowired val userService: UserService) {

    @GetMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserByUsername(@PathVariable username: String): ResponseEntity<User> {
        val user = userService.findUserByUsername(username)
        return when (user.isPresent) {
            true -> ResponseEntity(user.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("exists/{username}",produces = [MediaType.APPLICATION_JSON_VALUE])
    fun existsUserWithUsername(@PathVariable username: String):ResponseEntity<Boolean>{
        return ResponseEntity(userService.findUserByUsername(username).isPresent,HttpStatus.OK)
    }

    @DeleteMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUserByUsername(@PathVariable username: String): ResponseEntity<Any> {
        return when (userService.findUserByUsername(username).isPresent) {
            true -> {
                userService.deleteUserByUsername(username)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveUser(@RequestBody user: User): ResponseEntity<Any> {
        return when (userService.findUserByUsername(user.username).isPresent || userService.findUserByUserId(user.userId).isPresent) {
            false -> {
                userService.saveUser(user)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUser(@RequestBody user: User): ResponseEntity<Any> {
        return when (userService.findUserByUsername(user.username).isPresent) {
            true -> {
                userService.saveUser(user)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}