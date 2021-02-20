package com.fon.knjizararest.rest

import com.fon.knjizararest.dto.OrderRequest
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.entity.Order
import com.fon.knjizararest.entity.OrderItem
import com.fon.knjizararest.service.BookService
import com.fon.knjizararest.service.OrderService
import com.fon.knjizararest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v0/orders")
class OrderRestController @Autowired constructor(
        val orderService: OrderService,
        val userService: UserService,
        val bookService: BookService
) {

    @GetMapping("/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderByUsername(@PathVariable username: String): ResponseEntity<List<Order>> {
        val orders = orderService.findOrdersByUserUsername(username)
        return ResponseEntity(orders, HttpStatus.OK)
    }

    @GetMapping("/id/{orderId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOrderById(@PathVariable orderId: String): ResponseEntity<Order> {
        val order = orderService.findOrderByOrderId(orderId)
        if (order.isPresent) {
            return ResponseEntity(order.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveOrder(@RequestBody orderRequest: OrderRequest): ResponseEntity<Any> {
        return when (orderService.findOrderByOrderId(orderRequest.orderId).isPresent) {
            false -> {
                val user=userService.findUserByUsername(orderRequest.username)
                val books= mutableListOf<OrderItem>()
                val order=Order(orderId = orderRequest.orderId,user = user.get(),orderItems = books,dateCreated = Date(),total =orderRequest.items.sumByDouble {

                } )
                val orderItems=orderRequest.items.map {
                    OrderItem(
                            itemId = -1,
                            book = bookService.findBookByBookId(it.bookId).get(),
                            quantity = it.quantity,
                            order = null
                            )
                    bookService.findBookByBookId(it.bookId)
                }
                orderService.saveOrder()
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }


}