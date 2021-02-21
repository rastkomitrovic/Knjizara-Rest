package com.fon.knjizararest.service

import com.fon.knjizararest.dto.OrderRequest
import com.fon.knjizararest.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface OrderService {
    fun findOrderByOrderId(orderId: Long): Optional<Order> //default method
    fun saveOrder(orderRequest: OrderRequest) //default method
    fun findOrdersByUserUsername(username: String, pageable: Pageable): Page<Order>
}