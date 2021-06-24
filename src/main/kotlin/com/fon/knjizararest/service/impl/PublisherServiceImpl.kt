package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Publisher
import com.fon.knjizararest.repository.PublisherRepository
import com.fon.knjizararest.service.PublisherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PublisherServiceImpl(@Autowired val publisherRepository: PublisherRepository) : PublisherService {

    override fun findAllPublishers(): List<Publisher> {
        return publisherRepository.findAll().toList()
    }
}