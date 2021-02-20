package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.OrderItem
import com.fon.knjizararest.repository.OrderItemRepository
import com.fon.knjizararest.service.OrderItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderItemServiceImpl(@Autowired val orderItemRepository: OrderItemRepository) : OrderItemService {
    override fun saveOrderItem(orderItem: OrderItem) {
        orderItemRepository.save(orderItem)
    }

    override fun findOrderItemByOrderItemId(entryId: Long): Optional<OrderItem> {
        return orderItemRepository.findById(entryId)
    }

    override fun findOrderItemsByOrderId(basketId: Long, pageable: Pageable): Page<OrderItem> {
        return orderItemRepository.findOrderItemsByOrderId(basketId, pageable)
    }


    override fun deleteOrderItemByOrderEntryId(entryId: Long) {
        orderItemRepository.deleteById(entryId)
    }

}