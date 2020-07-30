package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.City
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : CrudRepository<City, Long> {
    fun findCitiesByCityNameContaining(param: String): List<City>
    fun existsCtyByCityNameOrPostalCode(cityName: String, postalCode: String): Boolean
}