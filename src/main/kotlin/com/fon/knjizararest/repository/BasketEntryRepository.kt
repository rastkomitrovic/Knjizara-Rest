package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.BasketEntry
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BasketEntryRepository : PagingAndSortingRepository<BasketEntry, Long> {
    fun findBasketEntriesByBasketBasketId(basketId: Long, pageable: Pageable): Page<BasketEntry>
    fun deleteBasketEntryByEntryId(entryId: Long)
}