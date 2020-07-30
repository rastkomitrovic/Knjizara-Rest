package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Country
import com.fon.knjizararest.service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/countries")
class CountryRestController(@Autowired val countryService: CountryService) {

    @GetMapping("/{countryId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findCountryByCountryId(@PathVariable countryId: Long): ResponseEntity<Country> {
        val country = countryService.findCountryByCountryId(countryId)
        return when (country.isPresent) {
            true -> ResponseEntity(country.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllCountries(): ResponseEntity<List<Country>> {
        val countries = countryService.findCountriesByCountryIdNotNull()
        return when (countries.isNotEmpty()) {
            true -> ResponseEntity(countries, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveCountry(@RequestBody country: Country): ResponseEntity<Any> {
        return when (countryService.existsCountryByCountryNameOrCountryNameShort(country.countryName, country.countryNameShort) || countryService.findCountryByCountryId(country.countryId).isPresent) {
            false -> {
                countryService.saveCountry(country)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCountry(@RequestBody country: Country): ResponseEntity<Any> {
        return when (countryService.findCountryByCountryId(country.countryId).isPresent) {
            true -> {
                countryService.saveCountry(country)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{countryId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteCountry(@PathVariable countryId: Long): ResponseEntity<Any> {
        return when (countryService.findCountryByCountryId(countryId).isPresent) {
            true -> {
                countryService.deleteCountryByCountryId(countryId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}