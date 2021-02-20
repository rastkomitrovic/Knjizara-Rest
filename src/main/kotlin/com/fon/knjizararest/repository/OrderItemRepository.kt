package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.OrderItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : PagingAndSortingRepository<OrderItem, Long> {

    @Query("select i from OrderItem as i where i.order.orderId=:orderId")
    fun findOrderItemsByOrderId(orderId: Long, pageable: Pageable): Page<OrderItem>
}