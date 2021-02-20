package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Order
import com.fon.knjizararest.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/orders")
class OrderRestController(@Autowired val orderService: OrderService) {

    @GetMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderByOrderId(@PathVariable username: String): ResponseEntity<List<Order>> {
        val orders = orderService.findOrdersByUserUsername(username)
        return ResponseEntity(orders, HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveOrder(@RequestBody order: Order): ResponseEntity<Any> {
        return when (orderService.findOrderByOrderId(order.orderId).isPresent) {
            false -> {
                orderService.saveOrder(order)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder(@RequestBody order: Order): ResponseEntity<Any> {
        return when (orderService.findOrderByOrderId(order.orderId).isPresent) {
            true -> {
                orderService.saveOrder(order)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{orderId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteOrder(@PathVariable orderId: Long): ResponseEntity<Any> {
        return when (orderService.findOrderByOrderId(orderId).isPresent) {
            true -> {
                orderService.deleteOrderByOrderId(orderId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

}