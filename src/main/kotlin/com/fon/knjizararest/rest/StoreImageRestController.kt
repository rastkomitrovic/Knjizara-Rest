package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.StoreImage
import com.fon.knjizararest.service.StoreImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/storeImages")
class StoreImageRestController(@Autowired val storeImageService: StoreImageService) {

    @GetMapping("/{storeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findStoreImagesByStoreId(@PathVariable storeId: Long): ResponseEntity<List<StoreImage>> {
        val images = storeImageService.findStoreImagesByStoreStoreId(storeId)
        return when (images.isNotEmpty()) {
            true -> ResponseEntity(images, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveStoreImage(storeImage: StoreImage): ResponseEntity<Any> {
        return when (storeImageService.findStoreImageByImageId(storeImage.imageId).isPresent) {
            false -> {
                storeImageService.saveStoreImage(storeImage)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @DeleteMapping("/{imageId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteStoreImage(@PathVariable imageId: Long): ResponseEntity<Any> {
        return when (storeImageService.findStoreImageByImageId(imageId).isPresent) {
            true -> {
                storeImageService.deleteStoreImageByImageId(imageId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}