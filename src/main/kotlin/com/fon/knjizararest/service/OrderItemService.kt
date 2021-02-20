package com.fon.knjizararest.service

import com.fon.knjizararest.entity.OrderItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface OrderItemService {
    fun saveOrderItem(orderItem: OrderItem) // default method
    fun findOrderItemByOrderItemId(entryId: Long): Optional<OrderItem>
    fun findOrderItemsByOrderId(basketId: Long, pageable: Pageable): Page<OrderItem>
    fun deleteOrderItemByOrderEntryId(entryId: Long)
}