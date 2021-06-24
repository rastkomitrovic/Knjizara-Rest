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

    override fun findAllCities(): List<City> {
        return cityRepository.findAll().toList()
    }

}