package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Publisher
import com.fon.knjizararest.repository.PublisherRepository
import com.fon.knjizararest.service.PublisherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PublisherServiceImpl(@Autowired val publisherRepository: PublisherRepository) : PublisherService {
    override fun savePublisher(publisher: Publisher) {
        publisherRepository.save(publisher)
    }

    override fun findAllPublishers(): List<Publisher> {
        return publisherRepository.findAll().toList()
    }

    override fun findPublisherByPublisherId(publisherId: Long): Optional<Publisher> {
        return publisherRepository.findById(publisherId)
    }

    override fun existsPublisherByName(publisherName: String): Boolean {
        return publisherRepository.existsPublisherByPublisherName(publisherName).isPresent
    }

    override fun deletePublisher(publisherId: Long) {
        publisherRepository.deleteById(publisherId)
    }

}