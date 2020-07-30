package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllAuthors(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Author>> {
        return ResponseEntity(authorService.findAllAuthors(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{page}/{size}/{sort}/{param}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllAuthorsSearch(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable param: String): ResponseEntity<Page<Author>> {
        return ResponseEntity(authorService.searchAuthors(param, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
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
        return when (authorService.findAuthorByAuthorId(author.authorId).isPresent) {
            true -> {
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