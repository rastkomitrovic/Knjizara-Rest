package com.fon.knjizararest.rest

import com.fon.knjizararest.dto.OrderRequest
import com.fon.knjizararest.entity.Order
import com.fon.knjizararest.entity.OrderItem
import com.fon.knjizararest.service.BookService
import com.fon.knjizararest.service.OrderService
import com.fon.knjizararest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*

@RestController
@RequestMapping("/api/v0/orders")
class OrderRestController @Autowired constructor(
        val orderService: OrderService,
        val userService: UserService,
        val bookService: BookService
) {

    @GetMapping("/{username}/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderByUsername(@PathVariable username: String, @PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<List<Order>> {
        val orders = orderService.findOrdersByUserUsername(username, PageRequest.of(page, size, Sort.by(sort)))
        return ResponseEntity(orders, HttpStatus.OK)
    }

    @GetMapping("/{orderId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderById(@PathVariable orderId: Long): ResponseEntity<Order> {
        val order = orderService.findOrderByOrderId(orderId)
        if (order.isPresent) {
            return ResponseEntity(order.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveOrder(@RequestBody orderRequest: OrderRequest): ResponseEntity<Any> {
        try {
            orderService.saveOrder(orderRequest)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ResponseEntity(ex.message, HttpStatus.NOT_ACCEPTABLE)
        }
        return ResponseEntity(HttpStatus.OK)
    }


}