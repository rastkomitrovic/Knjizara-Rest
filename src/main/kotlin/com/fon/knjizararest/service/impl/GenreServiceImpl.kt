package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Genre
import com.fon.knjizararest.repository.GenreRepository
import com.fon.knjizararest.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class GenreServiceImpl(@Autowired val genreRepository: GenreRepository) : GenreService {
    override fun saveGenre(genre: Genre) {
        genreRepository.save(genre)
    }

    override fun findAllGenres(): List<Genre> {
        return genreRepository.findAll().toList()
    }

    override fun findGenres(pageable: Pageable): Page<Genre> {
        return genreRepository.findAll(pageable)
    }

    override fun findGenreByGenreId(genreId: Long): Optional<Genre> {
        return genreRepository.findById(genreId)
    }

    override fun findGenresByGenreNameContainingOrDescriptionContaining(param: String, pageable: Pageable): Page<Genre> {
        return genreRepository.findGenresByGenreNameContainingOrDescriptionContaining(param, param, pageable)
    }

    override fun existsGenreByGenreName(genreName: String): Boolean {
        return genreRepository.existsGenreByGenreName(genreName)
    }

    override fun findGenresByBooks(book: Book): List<Genre> {
        return genreRepository.findGenresByBooks(book)
    }

    override fun deleteGenreByGenreId(genreId: Long) {
        return genreRepository.deleteGenreByGenreId(genreId)
    }
}