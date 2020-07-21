package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Basket
import com.fon.knjizararest.service.BasketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.print.attribute.standard.Media

@RestController
@RequestMapping("/api/v0/baskets")
class BasketRestController(@Autowired val basketService: BasketService) {

    @GetMapping("/{basketId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findBasketByBasketId(@PathVariable basketId: Long): ResponseEntity<Basket> {
        val basket = basketService.findBasketByBasketId(basketId)
        return when (basket.isPresent) {
            true -> ResponseEntity(basket.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBasket(@RequestBody basket: Basket): ResponseEntity<Any> {
        return when (basketService.findBasketByBasketId(basket.basketId).isPresent) {
            false -> {
                basketService.saveBasket(basket)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBasket(@RequestBody basket: Basket): ResponseEntity<Any> {
        return when (basketService.findBasketByBasketId(basket.basketId).isPresent) {
            true -> {
                basketService.saveBasket(basket)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{basketId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBasket(@PathVariable basketId: Long): ResponseEntity<Any> {
        return when (basketService.findBasketByBasketId(basketId).isPresent) {
            true -> {
                basketService.deleteBasketByBasketId(basketId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}