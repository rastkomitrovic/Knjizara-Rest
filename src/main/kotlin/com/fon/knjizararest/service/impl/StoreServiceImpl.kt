package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Store
import com.fon.knjizararest.repository.StoreRepository
import com.fon.knjizararest.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class StoreServiceImpl(@Autowired val storeRepository: StoreRepository) : StoreService {
    override fun findAllStores(): List<Store> {
        return storeRepository.findAll().toList()
    }

    override fun findStores(pageable: Pageable): Page<Store> {
        return storeRepository.findAll(pageable)
    }

    override fun saveStore(store: Store) {
        storeRepository.save(store)
    }

    override fun findStoreByStoreId(storeId: Long): Optional<Store> {
        return storeRepository.findById(storeId)
    }

    override fun existsStoreByAddressOrStoreName(address: String, storeName: String): Boolean {
        return storeRepository.existsStoreByAddressOrStoreName(address, storeName)
    }

    override fun findStoresByAddressContainingOrDescriptionContainingOrStoreNameContainingOrCityContaining(param: String, pageable: Pageable): Page<Store> {
        return storeRepository.findStoresByAddressContainingOrDescriptionContainingOrStoreNameContainingOrCityContaining(param, param, param, param, pageable)
    }

    override fun deleteStoreByStoreId(storeId: Long) {
        storeRepository.deleteStoreByStoreId(storeId)
    }
}