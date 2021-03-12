package com.asp.domain.usecase

import com.asp.domain.model.CountryModel
import com.asp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.asp.domain.Result

class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend fun getCountries(): Flow<Result<List<CountryModel>>> = flow {
        emit(Result.Loading)
        try{
            val result = Result.Success(repository.getCountries())
            emit(result)
        }catch (ex: Exception){
            emit(Result.Error(ex))
        }
    }
}