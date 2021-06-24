package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Publisher
import com.fon.knjizararest.service.PublisherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/publishers")
class PublisherRestController(@Autowired val publisherService: PublisherService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllPublishers(): ResponseEntity<List<Publisher>> {
        val publishers = publisherService.findAllPublishers()
        return when (publishers.isNotEmpty()) {
            true -> ResponseEntity(publishers, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

}