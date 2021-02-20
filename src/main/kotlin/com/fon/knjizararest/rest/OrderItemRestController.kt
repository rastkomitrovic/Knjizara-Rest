package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.OrderItem
import com.fon.knjizararest.service.OrderItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/orderItems")
class OrderItemRestController(@Autowired val orderItemService: OrderItemService) {

    @GetMapping("/{basketId}/{page}/{size}/{sort}/{active}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderItemsByOrderId(@PathVariable basketId: Long, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable active: Boolean): ResponseEntity<Page<OrderItem>> {
        return ResponseEntity(orderItemService.findOrderItemsByOrderId(basketId, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveBasketEntry(@RequestBody orderItem: OrderItem): ResponseEntity<Any> {
        return when (orderItemService.findOrderItemByOrderItemId(orderItem.itemId).isPresent) {
            false -> {
                orderItemService.saveOrderItem(orderItem)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBasketEntry(@RequestBody orderItem: OrderItem): ResponseEntity<Any> {
        return when (orderItemService.findOrderItemByOrderItemId(orderItem.itemId).isPresent) {
            true -> {
                orderItemService.saveOrderItem(orderItem)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{entryId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteBasketEntry(@PathVariable entryId: Long): ResponseEntity<Any> {
        return when (orderItemService.findOrderItemByOrderItemId(entryId).isPresent) {
            true -> {
                orderItemService.deleteOrderItemByOrderEntryId(entryId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}