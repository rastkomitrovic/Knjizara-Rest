package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Basket
import com.fon.knjizararest.repository.BasketRepository
import com.fon.knjizararest.service.BasketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BasketServiceImpl(@Autowired val basketRepository: BasketRepository) : BasketService {
    override fun findBasketByBasketId(basketId: Long): Optional<Basket> {
        return basketRepository.findById(basketId)
    }

    override fun saveBasket(basket: Basket) {
        basketRepository.save(basket)
    }

    override fun findBasketByUserUsername(username: String): Optional<Basket> {
        return basketRepository.findBasketByUserUsername(username)
    }

    override fun deleteBasketByBasketId(basketId: Long) {
        basketRepository.deleteBasketByBasketId(basketId)
    }

}