package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AuthorRepository : PagingAndSortingRepository<Author, Long> {
    fun findAuthorsByBooks(book: Book): List<Author>
    fun existsAuthorByFirstNameAndLastNameAndDateOfBirth(firstName: String, lastName: String, dateOfBirth: Date): Boolean
    fun deleteByAuthorId(authorId: Long)
}