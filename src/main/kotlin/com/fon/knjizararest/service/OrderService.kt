package com.fon.knjizararest.service

import com.fon.knjizararest.dto.OrderRequest
import com.fon.knjizararest.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderService {
    fun saveOrder(orderRequest: OrderRequest) //default method
    fun findOrdersByUserUsername(username: String, pageable: Pageable): Page<Order>
    fun findOrdersPaging(page: Int, size: Int, sort: String): Page<Order>
}