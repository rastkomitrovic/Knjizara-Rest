package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Publisher

interface PublisherService {
    fun findAllPublishers(): List<Publisher>
}