package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Store
import com.fon.knjizararest.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
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
}