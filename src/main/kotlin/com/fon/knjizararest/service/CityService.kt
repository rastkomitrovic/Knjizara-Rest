package com.fon.knjizararest.service

import com.fon.knjizararest.entity.City
import java.util.*

interface CityService {
    fun findCityByCityId(cityId: Long): Optional<City> //default method
    fun findAllCities(): List<City>
}