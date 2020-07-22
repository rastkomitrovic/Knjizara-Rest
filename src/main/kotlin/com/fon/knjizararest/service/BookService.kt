package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BookService {
    fun findAllBooks(): List<Book> //default method
    fun findBookByBookId(bookId: Long): Optional<Book> //default method
    fun saveBook(book: Book) //default method
    fun findBooks(pageable: Pageable): Page<Book> //default method
    fun findBooksByBookNameContainingOrAuthorsOrISBNEquals(param: String, pageable: Pageable): Page<Book>
    fun findBooksByAuthors(author: Author, pageable: Pageable): Page<Book>
    fun existsBookByBookNameOrISBN(bookName: String, ISBN: String): Boolean
    fun deleteBookByBookId(bookId: Long)
}