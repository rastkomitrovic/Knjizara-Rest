package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Store
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface StoreService {
    fun findAllStores(): List<Store> //default method
    fun findStores(pageable: Pageable): Page<Store> //default method
    fun saveStore(store: Store) //default method
    fun findStoreByStoreId(storeId: Long): Optional<Store> //default method
    fun existsStoreByAddressOrStoreName(address: String, storeName: String): Boolean
    fun findStoresByAddressContainingOrDescriptionContainingOrStoreNameContainingOrCityContaining(param: String, pageable: Pageable): Page<Store>
    fun deleteStoreByStoreId(storeId: Long)
}