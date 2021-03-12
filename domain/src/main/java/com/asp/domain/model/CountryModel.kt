package com.asp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryModel (
    val name: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: Long?,
    val area: Double,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val flag: String?
): Parcelable

@Parcelize
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
): Parcelable

@Parcelize
data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
): Parcelable
