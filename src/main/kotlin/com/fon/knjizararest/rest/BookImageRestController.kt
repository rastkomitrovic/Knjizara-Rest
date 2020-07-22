package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.BookImage
import com.fon.knjizararest.service.BookImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/bookImages")
class BookImageRestController(@Autowired val bookImageService: BookImageService) {

    /*@GetMapping("/{imageId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBookImageByImageId(@PathVariable imageId:Long):ResponseEntity<BookImage>{
        val image=bookImageService.findBookImageByImageId(imageId)
        return when (image.isPresent){
            true -> ResponseEntity(image.get(),HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }*/

    @GetMapping("/{bookId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBookImagesByBookId(@PathVariable bookId: Long): ResponseEntity<List<BookImage>> {
        val images = bookImageService.findBookImagesByBookBookId(bookId)
        return when (images.isNotEmpty()) {
            true -> ResponseEntity(images, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBookImage(@RequestBody bookImage: BookImage): ResponseEntity<Any> {
        return when (bookImageService.findBookImageByImageId(bookImage.imageId).isPresent) {
            false -> {
                bookImageService.saveBookImage(bookImage)
                ResponseEntity(HttpStatus.OK)
            }
            else -> {
                ResponseEntity(HttpStatus.FOUND)
            }
        }
    }

    @DeleteMapping("/{imageId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBookImageByImageId(@PathVariable imageId: Long): ResponseEntity<Any> {
        return when (bookImageService.findBookImageByImageId(imageId).isPresent) {
            true -> {
                bookImageService.deleteBookImageByImageId(imageId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.OK)
        }
    }
}