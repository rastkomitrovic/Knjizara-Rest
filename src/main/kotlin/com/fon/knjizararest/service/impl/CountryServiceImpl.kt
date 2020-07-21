package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Country
import com.fon.knjizararest.repository.CountryRepository
import com.fon.knjizararest.service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CountryServiceImpl(@Autowired val countryRepository: CountryRepository) : CountryService {
    override fun findCountryByCountryId(countryId: Long): Optional<Country> {
        return countryRepository.findById(countryId)
    }

    override fun saveCountry(country: Country) {
        countryRepository.save(country)
    }

    override fun deleteCountryByCountryId(countryId: Long) {
        countryRepository.deleteCountryByCountryId(countryId)
    }

    override fun findCountriesByCountryIdNotNull(): List<Country> {
        return countryRepository.findCountriesByCountryIdNotNull()
    }

    override fun existsCountryByCountryNameOrCountryNameShort(countryName: String, countryNameShort: String): Boolean {
        return countryRepository.existsCountryByCountryNameOrCountryNameShort(countryName, countryNameShort)
    }
}