package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Genre

interface GenreService {
    fun findAllGenres(): List<Genre> //default method
}