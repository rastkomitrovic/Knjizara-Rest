package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Publisher
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PublisherRepository : CrudRepository<Publisher, Long> {

    @Query("select p from Publisher as p where p.publisherName=:publisherName")
    fun existsPublisherByPublisherName(publisherName: String): Optional<Publisher>
}