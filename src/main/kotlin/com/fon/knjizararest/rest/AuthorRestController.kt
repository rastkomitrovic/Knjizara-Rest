package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/authors")
class AuthorRestController(@Autowired val authorService: AuthorService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllAuthorsNoPaging(): ResponseEntity<List<Author>> {
        val authors = authorService.findAllAuthors()
        return when (authors.isNotEmpty()) {
            true -> ResponseEntity(authors, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{authorId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAuthorWithId(@PathVariable authorId:Long):ResponseEntity<Author>{
        val author=authorService.findAuthorByAuthorId(authorId)
        return when(author.isPresent){
            true -> ResponseEntity(author.get(),HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveAuthor(@RequestBody author: Author): ResponseEntity<Any> {
        return when (authorService.existsAuthorByFirstNameAndLastNameAndDateOfBirth(author.firstName, author.lastName, author.dateOfBirth) || authorService.findAuthorByAuthorId(author.authorId).isPresent) {
            false -> {
                authorService.saveAuthor(author)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateAuthor(@RequestBody author: Author): ResponseEntity<Any> {
        val authorFromDb = authorService.findAuthorByAuthorId(author.authorId)
        return when (authorFromDb.isPresent) {
            true -> {
                author.books = authorFromDb.get().books
                authorService.saveAuthor(author)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{authorId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteAuthor(@PathVariable authorId: Long): ResponseEntity<Any> {
        return when (authorService.findAuthorByAuthorId(authorId).isPresent) {
            true -> {
                authorService.deleteByAuthorId(authorId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}