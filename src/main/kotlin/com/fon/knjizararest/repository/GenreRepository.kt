package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Genre
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : PagingAndSortingRepository<Genre, Long> {
}