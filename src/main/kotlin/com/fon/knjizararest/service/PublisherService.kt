package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Publisher
import java.util.*

interface PublisherService {
    fun savePublisher(publisher: Publisher)
    fun findAllPublishers(): List<Publisher>
    fun findPublisherByPublisherId(publisherId: Long): Optional<Publisher>
    fun existsPublisherByName(publisherName: String): Boolean
    fun deletePublisher(publisherId: Long)
}