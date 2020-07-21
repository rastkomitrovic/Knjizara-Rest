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
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCitiesByCityNameContaining(
            @RequestParam(name = "param", defaultValue = "") param: String
    ): ResponseEntity<List<City>> {
        return when (param) {
            "" -> {
                val cities = cityService.findAllByCityIdNotNull()
                if (cities.isNotEmpty())
                    ResponseEntity(cities, HttpStatus.OK)
                else
                    ResponseEntity(HttpStatus.NOT_FOUND)
            }
            else -> {
                val cities = cityService.findCitiesByCityNameContaining(param)
                if (cities.isNotEmpty())
                    ResponseEntity(cities, HttpStatus.OK)
                else
                    ResponseEntity(cities, HttpStatus.NOT_FOUND)
            }
        }
    }

    @DeleteMapping("/{cityId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteCityByCityId(@PathVariable cityId: Long): ResponseEntity<Any> {
        return when (cityService.findCityByCityId(cityId).isPresent) {
            true -> {
                cityService.deleteCityByCityId(cityId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveCity(@RequestBody city: City): ResponseEntity<Any> {
        return when (cityService.existsCtyByCityNameOrPostalCode(city.cityName, city.postalCode)) {
            false -> {
                cityService.saveCity(city)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCity(@RequestBody city: City): ResponseEntity<Any> {
        return when (cityService.findCityByCityId(city.cityId).isPresent) {
            true -> {
                cityService.saveCity(city)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}