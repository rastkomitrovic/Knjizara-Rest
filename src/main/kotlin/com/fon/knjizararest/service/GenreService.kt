package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Genre
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface GenreService {
    fun saveGenre(genre: Genre) //default method
    fun findAllGenres(): List<Genre> //default method
    fun findGenres(pageable: Pageable): Page<Genre> //default method
    fun findGenreByGenreId(genreId: Long): Optional<Genre> //default method
    fun findGenresByGenreNameContainingOrDescriptionContaining(param: String, pageable: Pageable): Page<Genre>
    fun existsGenreByGenreName(genreName: String): Boolean
    fun findGenresByBooks(book: Book): List<Genre>
    fun deleteGenreByGenreId(genreId: Long)
}