package com.asp.careernettest.data.service

import com.asp.careernettest.data.response.CountryResponse
import retrofit2.http.GET

interface CountryService {
    @GET("all")
    suspend fun getCountries(): List<CountryResponse>
}