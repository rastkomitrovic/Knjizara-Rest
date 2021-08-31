package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Author
import java.util.*

interface AuthorService {
    fun findAllAuthors(): List<Author> //default method
    fun findAuthorByAuthorId(authorId: Long): Optional<Author> //default method
    fun saveAuthor(author: Author) //default method
    fun existsAuthorByFirstNameAndLastNameAndDateOfBirth(firstName: String, lastName: String, dateOfBirth: Date): Boolean
    fun deleteByAuthorId(authorId: Long)

}