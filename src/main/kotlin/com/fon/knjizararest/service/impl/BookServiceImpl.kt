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
        val books=bookRepository.findAll().toList()
        books.forEach {
            var sum=0f
            it.comments.forEach { sum+= it.rating }
            it.rating=sum/it.comments.size
        }
        return books
    }

    override fun findBookByBookId(bookId: Long): Optional<Book> {
        val book= bookRepository.findById(bookId)
        return when (book.isPresent){
            true -> {
                var sum=0f
                book.get().comments.forEach { sum+=it.rating }
                book.get().rating=sum/book.get().comments.size
                book
            }
            else -> book
        }
    }

    override fun saveBook(book: Book) {
        bookRepository.save(book)
    }

    override fun findBooks(pageable: Pageable): Page<Book> {
        val page=bookRepository.findAll(pageable)
        page.forEach {
            var sum=0f
            it.comments.forEach { sum+= it.rating }
            it.rating=sum/it.comments.size
        }
        return page
    }

    override fun findBooksByBookNameContainingOrAuthorsOrISBNEquals(param: String, pageable: Pageable): Page<Book> {
        val page=bookRepository.findBooksByBookNameContainingOrISBNEquals(param, param, pageable)
        page.forEach {
            var sum=0f
            it.comments.forEach { sum+= it.rating }
            it.rating=sum/it.comments.size
        }
        return page
    }

    override fun findBooksByAuthors(author: Author, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByAuthors(author, pageable)
    }

    override fun existsBookByBookNameOrISBN(bookName: String, ISBN: String): Boolean {
        return bookRepository.existsBookByBookNameOrISBN(bookName, ISBN)
    }

    override fun deleteBookByBookId(bookId: Long) {
        bookRepository.deleteById(bookId)
    }
}