package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        return ResponseEntity(bookService.findBooksByBookNameContainingOrAuthorsOrISBNEquals(search, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping("/authorSearch/{page}/{size}/{sort}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBooksByAuthor(@RequestBody author: Author, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Book>> {
        return ResponseEntity(bookService.findBooksByAuthors(author, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBook(@RequestBody book: Book): ResponseEntity<Any> {
        return when (bookService.findBookByBookId(book.bookId).isPresent || bookService.existsBookByBookNameOrISBN(book.bookName, book.ISBN)) {
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