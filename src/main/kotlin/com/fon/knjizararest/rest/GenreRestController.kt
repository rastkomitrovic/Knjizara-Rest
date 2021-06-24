package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Genre
import com.fon.knjizararest.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
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
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}