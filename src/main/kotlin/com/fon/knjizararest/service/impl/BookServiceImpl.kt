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

    override fun findBooksSearch(param: String, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksSearch(param, pageable)
    }

    override fun findBooksByAuthors(authorId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByAuthors(authorId, pageable)
    }

    override fun findBooksByPublisher(publisherId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByPublisher(publisherId, pageable)
    }

    override fun existsBookISBN(ISBN: String): Boolean {
        return bookRepository.existsBookISBN(ISBN).isPresent
    }

    override fun deleteBookByBookId(bookId: Long) {
        bookRepository.deleteById(bookId)
    }

    override fun findBooksSearchSuggest(search: String, pageable: Pageable): List<Book> {
        return bookRepository.findBooksSearchSuggest(search,pageable)
    }

    override fun findBooksByGenre(genreId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByGenre(genreId, pageable)
    }
}