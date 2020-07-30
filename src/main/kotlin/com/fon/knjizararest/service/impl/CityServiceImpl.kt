package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.City
import com.fon.knjizararest.repository.CityRepository
import com.fon.knjizararest.service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CityServiceImpl(@Autowired val cityRepository: CityRepository) : CityService {
    override fun findCityByCityId(cityId: Long): Optional<City> {
        return cityRepository.findById(cityId)
    }

    override fun findCitiesByCityNameContaining(param: String): List<City> {
        return cityRepository.findCitiesByCityNameContaining(param)
    }

    override fun findAllByCityIdNotNull(): List<City> {
        return cityRepository.findAll().toList()
    }

    override fun deleteCityByCityId(cityId: Long) {
        cityRepository.deleteById(cityId)
    }

    override fun saveCity(city: City) {
        cityRepository.save(city)
    }

    override fun existsCtyByCityNameOrPostalCode(cityName: String, postalCode: String): Boolean {
        return cityRepository.existsCtyByCityNameOrPostalCode(cityName, postalCode);
    }


}