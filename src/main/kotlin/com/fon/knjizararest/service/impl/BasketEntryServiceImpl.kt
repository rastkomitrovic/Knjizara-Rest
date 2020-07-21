package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.BasketEntry
import com.fon.knjizararest.repository.BasketEntryRepository
import com.fon.knjizararest.service.BasketEntryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class BasketEntryServiceImpl(@Autowired val basketEntryRepository: BasketEntryRepository) : BasketEntryService {
    override fun saveBasketEntry(basketEntry: BasketEntry) {
        basketEntryRepository.save(basketEntry)
    }

    override fun findBasketEntryByEntryId(entryId: Long): Optional<BasketEntry> {
        return basketEntryRepository.findById(entryId)
    }

    override fun findBasketEntriesByBasketBasketId(basketId: Long, pageable: Pageable): Page<BasketEntry> {
        return basketEntryRepository.findBasketEntriesByBasketBasketId(basketId, pageable)
    }

    override fun deleteBasketEntryByEntryId(entryId: Long) {
        basketEntryRepository.deleteBasketEntryByEntryId(entryId)
    }

}