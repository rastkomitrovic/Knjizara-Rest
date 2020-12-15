package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Genre
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

    @GetMapping("/{publisherId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findGenreByGenreId(@PathVariable publisherId: Long): ResponseEntity<Publisher> {
        val publisher = publisherService.findPublisherByPublisherId(publisherId)
        return when (publisher.isPresent) {
            true -> ResponseEntity(publisher.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveGenre(@RequestBody publisher: Publisher): ResponseEntity<Any> {
        return when (publisherService.existsPublisherByName(publisher.publisherName) || publisherService.findPublisherByPublisherId(publisher.publisherId).isPresent) {
            false -> {
                publisherService.savePublisher(publisher)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateGenre(publisher: Publisher): ResponseEntity<Any> {
        return when (publisherService.findPublisherByPublisherId(publisher.publisherId).isPresent) {
            true -> {
                publisherService.savePublisher(publisher)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{publisherId}")
    fun deleteGenre(@PathVariable publisherId: Long): ResponseEntity<Any> {
        return when (publisherService.findPublisherByPublisherId(publisherId).isPresent) {
            true -> {
                publisherService.deletePublisher(publisherId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}