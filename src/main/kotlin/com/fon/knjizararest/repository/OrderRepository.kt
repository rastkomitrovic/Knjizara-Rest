package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : PagingAndSortingRepository<Order, Long> {
    @Query("Select o from Order as o where o.user.username=:username")
    fun findOrdersByUsername(@Param("username")username:String, pageable: Pageable):Page<Order>

    @Query("Select o from Order as o where o.payPalOrderId=:payPalOrderId")
    fun findOrderByPayPalOrderId(@Param("payPalOrderId")payPalOrderId:String):Optional<Order>
}