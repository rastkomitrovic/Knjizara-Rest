package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Store
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : PagingAndSortingRepository<Store, Long> {
}