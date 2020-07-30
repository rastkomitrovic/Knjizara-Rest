package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.BasketEntry
import com.fon.knjizararest.service.BasketEntryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/basketEntries")
class BasketEntryRestController(@Autowired val basketEntryService: BasketEntryService) {

    @GetMapping("/{basketId}/{page}/{size}/{sort}/{active}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBasketEntriesByBasketBasketId(@PathVariable basketId: Long, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable active: Boolean): ResponseEntity<Page<BasketEntry>> {
        return ResponseEntity(basketEntryService.findBasketEntriesByBasketBasketIdAndActiveEquals(basketId, active, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBasketEntry(@RequestBody basketEntry: BasketEntry): ResponseEntity<Any> {
        return when (basketEntryService.findBasketEntryByEntryId(basketEntry.entryId).isPresent) {
            false -> {
                basketEntryService.saveBasketEntry(basketEntry)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBasketEntry(@RequestBody basketEntry: BasketEntry): ResponseEntity<Any> {
        return when (basketEntryService.findBasketEntryByEntryId(basketEntry.entryId).isPresent) {
            true -> {
                basketEntryService.saveBasketEntry(basketEntry)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{entryId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBasketEntry(@PathVariable entryId: Long): ResponseEntity<Any> {
        return when (basketEntryService.findBasketEntryByEntryId(entryId).isPresent) {
            true -> {
                basketEntryService.deleteBasketEntryByEntryId(entryId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}