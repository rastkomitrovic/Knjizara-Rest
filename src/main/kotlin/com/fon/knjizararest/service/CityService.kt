package com.fon.knjizararest.service

import com.fon.knjizararest.entity.City
import java.util.*

interface CityService {
    fun findCityByCityId(cityId: Long): Optional<City> //default method
    fun saveCity(city: City) //default method
    fun findCitiesByCityNameContaining(param: String): List<City>
    fun findAllByCityIdNotNull(): List<City>
    fun deleteCityByCityId(cityId: Long)
    fun existsCtyByCityNameOrPostalCode(cityName: String, postalCode: String): Boolean
}