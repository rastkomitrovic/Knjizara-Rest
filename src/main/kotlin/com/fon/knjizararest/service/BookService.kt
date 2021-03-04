package com.fon.knjizararest.service

import com.fon.knjizararest.dto.BookRequest
import com.fon.knjizararest.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BookService {
    fun findAllBooks(): List<Book> //default method
    fun findBookByBookId(bookId: Long): Optional<Book> //default method
    fun saveBook(bookRequest: BookRequest) //default method
    fun updateBook(book:Book)
    fun findBooks(pageable: Pageable): Page<Book> //default method
    fun findBooksSearch(param: String, pageable: Pageable): Page<Book>
    fun findBooksByAuthors(authorId: Long, pageable: Pageable): Page<Book>
    fun findBooksByPublisher(publisherId: Long, pageable: Pageable): Page<Book>
    fun findBooksByGenre(genreId: Long, pageable: Pageable): Page<Book>
    fun existsBookISBN(ISBN: String): Boolean
    fun deleteBookByBookId(bookId: Long)
    fun findBooksSearchSuggest(search: String, pageable: Pageable): List<Book>
}