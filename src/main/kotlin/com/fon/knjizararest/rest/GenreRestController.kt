package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Genre
import com.fon.knjizararest.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/genres")
class GenreRestController(@Autowired val genreService: GenreService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllGenresNoPaging(): ResponseEntity<List<Genre>> {
        val genres = genreService.findAllGenres()
        return when (genres.isNotEmpty()) {
            true -> ResponseEntity(genres, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/{genreId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findGenreByGenreId(@PathVariable genreId: Long): ResponseEntity<Genre> {
        val genre = genreService.findGenreByGenreId(genreId)
        return when (genre.isPresent) {
            true -> ResponseEntity(genre.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllGenresPaging(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Genre>> {
        return ResponseEntity(genreService.findGenres(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{page}/{size}/{sort}/{search}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllGenresPagingAndSearching(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable search: String): ResponseEntity<Page<Genre>> {
        return ResponseEntity(genreService.findGenresByGenreNameContainingOrDescriptionContaining(search, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveGenre(@RequestBody genre: Genre): ResponseEntity<Any> {
        return when (genreService.existsGenreByGenreName(genre.genreName) || genreService.findGenreByGenreId(genre.genreId).isPresent) {
            false -> {
                genreService.saveGenre(genre)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateGenre(genre: Genre): ResponseEntity<Any> {
        return when (genreService.findGenreByGenreId(genre.genreId).isPresent) {
            true -> {
                genreService.saveGenre(genre)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{genreId}")
    fun deleteGenre(@PathVariable genreId: Long): ResponseEntity<Any> {
        return when (genreService.findGenreByGenreId(genreId).isPresent) {
            true -> {
                genreService.deleteGenreByGenreId(genreId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}