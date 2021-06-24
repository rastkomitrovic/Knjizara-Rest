package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Publisher
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PublisherRepository : CrudRepository<Publisher, Long> {
}