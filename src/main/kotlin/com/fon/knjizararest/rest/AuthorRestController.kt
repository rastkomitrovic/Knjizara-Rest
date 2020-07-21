package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.service.AuthorService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v0/authors")
class AuthorRestController(@Autowired val authorService: AuthorService) {

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllAuthors(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Author>> {
        return ResponseEntity(authorService.findAllAuthors(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveAuthor(@RequestBody author: Author): ResponseEntity<Any> {
        return when (authorService.existsAuthorByFirstNameAndLastNameAndDateOfBirth(author.firstName, author.lastName, author.dateOfBirth)) {
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
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{authorId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteAuthor(@PathVariable authorId: Long): ResponseEntity<Any> {
        return when (authorService.findAuthorByAuthorId(authorId).isPresent) {
            true -> {
                authorService.deleteByAuthorId(authorId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}