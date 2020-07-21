package com.fon.knjizararest.service

import com.fon.knjizararest.entity.BasketEntry
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BasketEntryService {
    fun saveBasketEntry(basketEntry: BasketEntry) // default method
    fun findBasketEntryByEntryId(entryId: Long): Optional<BasketEntry>
    fun findBasketEntriesByBasketBasketId(basketId: Long, pageable: Pageable): Page<BasketEntry>
    fun deleteBasketEntryByEntryId(entryId: Long)
}