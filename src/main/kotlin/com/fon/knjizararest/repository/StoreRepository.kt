package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Store
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : PagingAndSortingRepository<Store, Long> {
    fun existsStoreByAddressOrStoreName(address: String, storeName: String): Boolean
    fun findStoresByAddressContainingOrDescriptionContainingOrStoreNameContainingOrCityContaining(address: String, description: String, storeName: String, city: String, pageable: Pageable): Page<Store>
}