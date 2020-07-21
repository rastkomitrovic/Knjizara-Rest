package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Country
import java.util.*

interface CountryService {
    fun findCountryByCountryId(countryId: Long): Optional<Country> //default method
    fun saveCountry(country: Country) //default method
    fun deleteCountryByCountryId(countryId: Long)
    fun findCountriesByCountryIdNotNull(): List<Country>
    fun existsCountryByCountryNameOrCountryNameShort(countryName: String, countryNameShort: String): Boolean
}