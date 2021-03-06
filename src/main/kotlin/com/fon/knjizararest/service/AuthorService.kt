package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface AuthorService {
    fun findAllAuthors(): List<Author> //default method
    fun findAllAuthors(pageable: Pageable): Page<Author> //default method
    fun findAuthorByAuthorId(authorId: Long): Optional<Author> //default method
    fun saveAuthor(author: Author) //default method
    fun existsAuthorByFirstNameAndLastNameAndDateOfBirth(firstName: String, lastName: String, dateOfBirth: Date): Boolean
    fun deleteByAuthorId(authorId: Long)

}