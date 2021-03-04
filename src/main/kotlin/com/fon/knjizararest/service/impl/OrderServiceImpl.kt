package com.fon.knjizararest.service.impl

import com.fon.knjizararest.dto.OrderRequest
import com.fon.knjizararest.entity.Order
import com.fon.knjizararest.entity.OrderItem
import com.fon.knjizararest.entity.User
import com.fon.knjizararest.repository.BookRepository
import com.fon.knjizararest.repository.OrderRepository
import com.fon.knjizararest.repository.UserRepository
import com.fon.knjizararest.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderServiceImpl @Autowired constructor(
         private val orderRepository: OrderRepository,
         private val userRepository: UserRepository,
         private val bookRepository: BookRepository
) : OrderService {
    override fun findOrderByOrderId(orderId: Long): Optional<Order> {
        return orderRepository.findById(orderId)
    }

    override fun saveOrder(orderRequest: OrderRequest) {
        checkIfOrderExists(orderRequest)
        val user=getUserForUsername(orderRequest.username)
        val orderItems =mapOrderItems(orderRequest)
        val order = Order(
                orderId = 0,
                payPalOrderId = orderRequest.orderId,
                user = user,
                orderItems = orderItems,
                dateCreated = Date(),
                total = orderItems.sumByDouble {
                    it.book.price * it.quantity
                })
        orderItems.forEach { it.order = order }
        orderRepository.save(order)
    }

    override fun findOrdersByUserUsername(username: String, pageable: Pageable): Page<Order> {
        return orderRepository.findOrdersByUsername(username, pageable)
    }

    private fun checkIfOrderExists(orderRequest: OrderRequest){
        if(orderRepository.findOrderByPayPalOrderId(orderRequest.orderId).isPresent)
            throw Exception("The specified pay pall order id:${orderRequest.orderId} already exists")
    }

    private fun getUserForUsername(username: String):User{
        val user=userRepository.findUserByUsername(username)
        if(user.isEmpty)
            throw Exception("The specified username:${username} doesnt exist")
        return user.get()
    }

    private fun mapOrderItems(orderRequest: OrderRequest):List<OrderItem>{
        return orderRequest.items.map {
            val book= bookRepository.findById(it.bookId)
            if(book.isEmpty)
                throw Exception("The specified book with id:${it.bookId} doesnt exist")
            OrderItem(
                    itemId = 0,
                    book = book.get(),
                    quantity = it.quantity,
                    order = null
            )
        }
    }
}