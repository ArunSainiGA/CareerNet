package com.asp.careernettest.data.mapper

import com.asp.careernettest.data.response.CountryResponse
import com.asp.domain.model.CountryModel
import com.asp.domain.model.Currency
import com.asp.domain.model.Language

class CountryMapper: Mapper<CountryResponse, CountryModel> {
    override fun map(input: CountryResponse): CountryModel {
        return CountryModel(
            input.name,
            input.capital,
            input.region ?: "",
            input.subregion ?: "",
            input.population,
            input.area ?: 0.0,
            input.currencies?.map {
                Currency(
                    it.code ?: "",
                    it.name ?: "",
                    it.symbol ?: ""
                )
            } ?: emptyList(),
            input.languages?.map {
                Language(
                    it.iso639_1 ?: "",
                    it.iso639_2 ?: "",
                    it.name ?: "",
                    it.nativeName ?: ""
                )
            } ?: emptyList(),
            input.flag
        )
    }
}