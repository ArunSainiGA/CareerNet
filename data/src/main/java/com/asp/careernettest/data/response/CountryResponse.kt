package com.asp.careernettest.data.response

data class CountryResponse (
    val name: String,
    val topLevelDomain: List<String>?,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>?,
    val capital: String,
    val altSpellings: List<String>?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val latlng: List<Double>?,
    val demonym: String?,
    val area: Double?,
    val gini: Double?,
    val timezones: List<String>?,
    val borders: List<String>?,
    val nativeName: String?,
    val numericCode: String?,
    val currencies: List<Currency>?,
    val languages: List<Language>?,
    val translations: Translation?,
    val flag: String?,
    val regionalBlocs: List<RegionalBlock>?
    )

    data class Currency(
        val code: String?,
        val name: String?,
        val symbol: String?
    )

    data class Language(
        val iso639_1: String?,
        val iso639_2: String?,
        val name: String?,
        val nativeName: String?
    )

    data class Translation(
        val de: String?,
        val es: String?,
        val fr: String?,
        val ja: String?,
        val it: String?,
        val br: String?,
        val pt: String?,
        val nl: String?,
        val hr: String?,
        val fa: String?
    )

    data class RegionalBlock(
        val acronym: String?,
        val name: String?,
        val otherAcronyms: List<String>?,
        val otherNames: List<String>?
    )