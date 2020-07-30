package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Store
import com.fon.knjizararest.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/stores")
class StoreRestController(@Autowired val storeService: StoreService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllStores(): ResponseEntity<List<Store>> {
        val stores = storeService.findAllStores();
        return when (stores.isNotEmpty()) {
            true -> ResponseEntity(stores, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllStoresPaging(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Store>> {
        return ResponseEntity(storeService.findStores(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{page}/{size}/{sort}/{search}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllStoresPagingSearch(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable search: String): ResponseEntity<Page<Store>> {
        return ResponseEntity(storeService.findStoresByAddressContainingOrDescriptionContainingOrStoreNameContainingOrCityContaining(search, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{storeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findStoreByStoreId(@PathVariable storeId: Long): ResponseEntity<Store> {
        val store = storeService.findStoreByStoreId(storeId)
        return when (store.isPresent) {
            true -> ResponseEntity(store.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveStore(store: Store): ResponseEntity<Any> {
        return when (storeService.findStoreByStoreId(store.storeId).isPresent || storeService.existsStoreByAddressOrStoreName(store.address, store.storeName)) {
            false -> {
                storeService.saveStore(store)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateStore(store: Store): ResponseEntity<Any> {
        return when (storeService.findStoreByStoreId(store.storeId).isPresent) {
            true -> {
                storeService.saveStore(store)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{storeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteStore(@PathVariable storeId: Long): ResponseEntity<Any> {
        return when (storeService.findStoreByStoreId(storeId).isPresent) {
            true -> {
                storeService.deleteStoreByStoreId(storeId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}