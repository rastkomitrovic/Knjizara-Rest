package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.repository.BookRepository
import com.fon.knjizararest.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookServiceImpl(@Autowired val bookRepository: BookRepository) : BookService {
    override fun findAllBooks(): List<Book> {
        return bookRepository.findAll().toList()
    }

    override fun findBookByBookId(bookId: Long): Optional<Book> {
        return bookRepository.findById(bookId)
    }

    override fun saveBook(book: Book) {
        bookRepository.save(book)
    }

    override fun findBooks(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    override fun findBooksByBookNameContainingOrAuthorsOrISBNEquals(param: String, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByBookNameContainingOrISBNEquals(param, param, pageable)
    }

    override fun findBooksByAuthors(author: Author, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByAuthors(author, pageable)
    }

    override fun existsBookByBookNameOrISBN(bookName: String, ISBN: String): Boolean {
        return bookRepository.existsBookByBookNameOrISBN(bookName, ISBN)
    }

    override fun deleteBookByBookId(bookId: Long) {
        bookRepository.deleteBookByBookId(bookId)
    }
}