package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Order
import java.util.*

interface OrderService {
    fun findOrderByOrderId(orderId: Long): Optional<Order> //default method
    fun saveOrder(order: Order) //default method
    fun findOrdersByUserUsername(username: String): List<Order>
    fun deleteOrderByOrderId(orderId: Long)
}