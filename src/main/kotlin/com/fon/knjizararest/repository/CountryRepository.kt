package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : CrudRepository<Country, Long> {
    fun deleteCountryByCountryId(countryId: Long)
    fun findCountriesByCountryIdNotNull(): List<Country>
    fun existsCountryByCountryNameOrCountryNameShort(countryName: String, countryNameShort: String): Boolean
}