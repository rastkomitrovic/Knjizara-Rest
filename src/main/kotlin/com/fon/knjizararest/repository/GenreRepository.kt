package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Genre
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : PagingAndSortingRepository<Genre, Long> {
    fun existsGenreByGenreName(genreName: String): Boolean
    fun findGenresByBooks(book: Book): List<Genre>
    fun findGenresByGenreNameContainingOrDescriptionContaining(genreName: String, description: String, pageable: Pageable): Page<Genre>
}