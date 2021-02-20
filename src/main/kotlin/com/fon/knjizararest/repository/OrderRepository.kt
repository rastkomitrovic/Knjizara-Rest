package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<Order, String> {
    @Query("Select o from Order as o where o.user.username=:username")
    fun findOrdersByUsername(username:String):List<Order>
}