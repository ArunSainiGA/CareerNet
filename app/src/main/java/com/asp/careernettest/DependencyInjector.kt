package com.asp.careernettest

import android.app.Application
import com.asp.careernettest.data.mapper.CountryMapper
import com.asp.careernettest.data.repository.CountryRepositoryImpl
import com.asp.careernettest.data.service.CountryService
import com.asp.careernettest.utils.Constant
import com.asp.domain.repository.CountryRepository
import com.asp.domain.usecase.GetCountriesUseCase
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DependencyInjector{

    private lateinit var retrofit: Retrofit

    fun init(application: Application){
        retrofit = Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                newRequest.addHeader("Content-Type", "application/json")
                newRequest.addHeader("Accept", "application/json")
                chain.proceed(newRequest.build())
            }.build()
    }

    private fun getCountryService() = retrofit.create(CountryService::class.java)

    fun getCountryUseCase() = GetCountriesUseCase(CountryRepositoryImpl(getCountryService(), CountryMapper()) as CountryRepository)
}