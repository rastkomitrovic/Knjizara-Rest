package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.City
import com.fon.knjizararest.service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/cities")
class CityRestController(@Autowired val cityService: CityService) {

    @GetMapping("/{cityId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCityByCityId(@PathVariable cityId: Long): ResponseEntity<City> {
        val city = cityService.findCityByCityId(cityId)
        return when (city.isPresent) {
            true -> ResponseEntity(city.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllCities(): ResponseEntity<List<City>> {
        val cities = cityService.findAllCities()
        return if (cities.isNotEmpty())
            ResponseEntity(cities, HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.NO_CONTENT)

    }
}