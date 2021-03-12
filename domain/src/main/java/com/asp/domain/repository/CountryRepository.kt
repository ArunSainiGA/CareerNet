package com.asp.domain.repository

import com.asp.domain.model.CountryModel

interface CountryRepository {
    suspend fun getCountries(): List<CountryModel>
}