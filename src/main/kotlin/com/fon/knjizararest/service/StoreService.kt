package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Store

interface StoreService {
    fun findAllStores(): List<Store> //default method
}