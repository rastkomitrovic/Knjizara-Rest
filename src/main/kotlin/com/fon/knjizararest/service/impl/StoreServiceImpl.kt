package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Store
import com.fon.knjizararest.repository.StoreRepository
import com.fon.knjizararest.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StoreServiceImpl(@Autowired val storeRepository: StoreRepository) : StoreService {
    override fun findAllStores(): List<Store> {
        return storeRepository.findAll().toList()
    }
}