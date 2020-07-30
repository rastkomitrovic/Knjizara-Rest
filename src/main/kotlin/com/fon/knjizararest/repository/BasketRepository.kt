package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Basket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BasketRepository : CrudRepository<Basket, Long> {
    fun findBasketByUserUsername(username: String): Optional<Basket>
}