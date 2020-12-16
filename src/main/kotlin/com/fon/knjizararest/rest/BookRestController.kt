package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.service.BookService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.swing.SortOrder

@RestController
@RequestMapping("/api/v0/books")
class BookRestController(@Autowired val bookService: BookService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllBooks(): ResponseEntity<List<Book>> {
        val books = bookService.findAllBooks()
        return when (books.isNotEmpty()) {
            true -> ResponseEntity(books, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/top10", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBestReviewBooks(): ResponseEntity<List<Book>> {
        val books = bookService.findAllBooks()
        books.sortedWith(Comparator<Book> { b1, b2 ->
            when (b1.rating > b2.rating) {
                true -> -1
                false -> 1
            }
        }
        )
        return when (books.isNotEmpty()) {
            true -> when (books.size >= 10) {
                true -> ResponseEntity(books.subList(0, 10), HttpStatus.OK)
                else -> ResponseEntity(books.subList(0, books.size), HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{bookId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBookByBookId(@PathVariable bookId: Long): ResponseEntity<Book> {
        val book = bookService.findBookByBookId(bookId)
        return when (book.isPresent) {
            true -> ResponseEntity(book.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksPaging(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooks(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{page}/{size}/{sort}/{search}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksPagingSearch(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable search: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooksSearch(search, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/searchSuggest/{search}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksSearchSuggest(@PathVariable search: String): ResponseEntity<List<Book>> {
        return ResponseEntity(bookService.findBooksSearchSuggest(search, PageRequest.of(0, 4, Sort.by("rating").descending())), HttpStatus.OK)
    }

    @GetMapping("/authorSearch/{authorId}/{page}/{size}/{sort}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksByAuthor(@PathVariable authorId: Long, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooksByAuthors(authorId, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/publisherSearch/{publisherId}/{page}/{size}/{sort}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksByPublisher(@PathVariable publisherId: Long, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooksByPublisher(publisherId, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/genreSearch/{genreId}/{page}/{size}/{sort}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksByGenre(@PathVariable genreId: Long, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooksByGenre(genreId, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/existsByIsbn/{ISBN}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun existsBookByISBN(@PathVariable ISBN: String): ResponseEntity<Boolean> {
        return ResponseEntity(bookService.existsBookISBN(ISBN), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBook(@RequestBody book: Book): ResponseEntity<Any> {
        return when (bookService.findBookByBookId(book.bookId).isPresent || bookService.existsBookISBN(book.ISBN)) {
            false -> {
                bookService.saveBook(book)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBook(@RequestBody book: Book): ResponseEntity<Any> {
        return when (bookService.findBookByBookId(book.bookId).isPresent) {
            true -> {
                bookService.saveBook(book)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{bookId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBook(@PathVariable bookId: Long): ResponseEntity<Any> {
        return when (bookService.findBookByBookId(bookId).isPresent) {
            true -> {
                bookService.deleteBookByBookId(bookId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}