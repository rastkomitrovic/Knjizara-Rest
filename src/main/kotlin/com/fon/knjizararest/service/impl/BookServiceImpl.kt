package com.fon.knjizararest.service.impl

import com.fon.knjizararest.dto.BookRequest
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.BookImage
import com.fon.knjizararest.repository.*
import com.fon.knjizararest.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookServiceImpl @Autowired constructor(
        private val bookRepository: BookRepository,
        private val genreRepository: GenreRepository,
        private val authorRepository: AuthorRepository,
        private val publisherRepository: PublisherRepository,
        private val bookImageRepository: BookImageRepository
) : BookService {
    override fun findAllBooks(): List<Book> {
        return bookRepository.findAll().toList()
    }

    override fun findBookByBookId(bookId: Long): Optional<Book> {
        return bookRepository.findById(bookId)
    }

    override fun saveBook(bookRequest: BookRequest) {
        val book=mapToBook(bookRequest)
        bookRepository.save(book)
        book.images.forEach { bookImageRepository.save(it) }
    }

    override fun findBooks(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    override fun findBooksSearch(param: String, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksSearch(param, pageable)
    }

    override fun findBooksByAuthor(authorId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByAuthors(authorId, pageable)
    }

    override fun findBooksByPublisher(publisherId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByPublisher(publisherId, pageable)
    }

    override fun existsBookISBN(ISBN: String): Boolean {
        return bookRepository.existsBookISBN(ISBN).isPresent
    }

    override fun findBooksSearchSuggest(search: String, pageable: Pageable): List<Book> {
        return bookRepository.findBooksSearchSuggest(search,pageable)
    }

    override fun findBooksByGenre(genreId: Long, pageable: Pageable): Page<Book> {
        return bookRepository.findBooksByGenre(genreId, pageable)
    }

    private fun mapToBook(bookRequest: BookRequest):Book{
        val book= Book(
                bookId = 0,
                ISBN = bookRequest.ISBN,
                bookName = bookRequest.bookName,
                description = bookRequest.description,
                price = bookRequest.price,
                stock = bookRequest.stock,
                numberOfSoldCopies = 0,
                language = bookRequest.language,
                images = bookRequest.images?.map {
                    BookImage(
                            imageId = 0,
                            imageEncoding = "",
                            imageUrl = it,
                            book = null
                    )
                }.orEmpty(),
                genres = bookRequest.genres.map { genreRepository.findById(it).get() },
                authors = bookRequest.authors.map { authorRepository.findById(it).get() },
                comments = emptyList(),
                rating = 0F,
                publisher = publisherRepository.findById(bookRequest.publisher).get()
        )
        book.images.forEach { it.book=book }
        book.genres.forEach { it.books = it.books.plus(book) }
        book.authors.forEach { it.books = it.books.plus(book) }
        return book
    }
}