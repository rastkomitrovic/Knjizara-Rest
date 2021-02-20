package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Order
import com.fon.knjizararest.repository.OrderRepository
import com.fon.knjizararest.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(@Autowired val orderRepository: OrderRepository) : OrderService {
    override fun findOrderByOrderId(orderId: String): Optional<Order> {
        return orderRepository.findById(orderId)
    }

    override fun saveOrder(order: Order) {
        orderRepository.save(order)
    }

    override fun findOrdersByUserUsername(username: String): List<Order> {
        return orderRepository.findOrdersByUsername(username)
    }

    override fun deleteOrderByOrderId(orderId: String) {
        orderRepository.deleteById(orderId)
    }

}