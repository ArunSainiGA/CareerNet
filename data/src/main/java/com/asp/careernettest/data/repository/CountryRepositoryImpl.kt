package com.asp.careernettest.data.repository

import com.asp.careernettest.data.mapper.CountryMapper
import com.asp.careernettest.data.service.CountryService
import com.asp.domain.model.CountryModel
import com.asp.domain.repository.CountryRepository

class CountryRepositoryImpl(private val service: CountryService, private val mapper: CountryMapper): CountryRepository {
    override suspend fun getCountries(): List<CountryModel> {
        return service.getCountries().map {
            mapper.map(it)
        }
    }
}