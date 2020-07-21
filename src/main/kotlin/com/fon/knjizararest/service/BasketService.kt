package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Basket
import java.util.*

interface BasketService {
    fun findBasketByBasketId(basketId: Long): Optional<Basket> //default method
    fun saveBasket(basket: Basket) //default method
    fun findBasketByUserUsername(username: String): Optional<Basket>
    fun deleteBasketByBasketId(basketId: Long)
}