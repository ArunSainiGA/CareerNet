package com.asp.careernettest.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asp.careernettest.utils.SingleLiveEvent
import com.asp.domain.Result
import com.asp.domain.model.CountryModel
import com.asp.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    private val actions: SingleLiveEvent<ViewActions> = SingleLiveEvent()

    val viewStateObservable: LiveData<ViewState> = viewState
    val viewActionsObservable: LiveData<ViewActions> = actions

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewState.value = ViewState.Error(throwable?.message ?: "There was some error")
    }
    private val context = coroutineExceptionHandler

    init {
        viewModelScope.launch(context){
            getCountriesUseCase.getCountries().collect {
                when(it){
                    is Result.Success -> {
                        viewState.value = ViewState.CountriesFetched(it.data)
                    }
                    is Result.Error -> {
                        viewState.value = ViewState.Error(it.exception.localizedMessage)
                    }
                    is Result.Loading -> {
                        viewState.value = ViewState.Loading(true)
                    }
                }
            }
        }
    }

    sealed class ViewState{
        data class Loading(val isLoading: Boolean = false): ViewState()
        data class CountriesFetched(val data: List<CountryModel>): ViewState()
        data class Error(val message: String): ViewState()
    }

    sealed class ViewActions{
        object Search: ViewActions()
    }
}