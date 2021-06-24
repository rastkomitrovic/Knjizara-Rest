package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Genre
import com.fon.knjizararest.repository.GenreRepository
import com.fon.knjizararest.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(@Autowired val genreRepository: GenreRepository) : GenreService {

    override fun findAllGenres(): List<Genre> {
        return genreRepository.findAll().sortedBy { it.genreName }.toList()
    }
}