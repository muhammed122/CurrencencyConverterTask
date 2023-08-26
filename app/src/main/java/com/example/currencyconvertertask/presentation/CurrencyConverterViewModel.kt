package com.example.currencyconvertertask.presentation

import androidx.lifecycle.viewModelScope
import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.entity.response.CurrencySymbolsModel
import com.example.currencyconvertertask.domin.usecase.ConvertCurrencyUseCase
import com.example.currencyconvertertask.domin.usecase.GetAvailableCurrencyUseCase
import com.example.currencyconvertertask.ui.base.BaseViewModel
import com.example.currencyconvertertask.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val getAvailableCurrencyUseCase: GetAvailableCurrencyUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : BaseViewModel() {

    init {
        getCurrencies()
    }

    private val _currenciesFlow = MutableSharedFlow<Resource<CurrencySymbolsModel?>>()
    val currenciesFlow get() = _currenciesFlow
    private fun getCurrencies() {
        getAvailableCurrencyUseCase.invoke(Unit).onEach {
            _currenciesFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    private val _convertCurrencyFlow = MutableSharedFlow<Resource<CurrencyConverterModel>>()
    val convertCurrencyFlow get() = _convertCurrencyFlow

    private val _request = ConvertCurrencyRequest()
    fun getCurrencyConverterRequest()=_request
    fun convertCurrency() {
        if (_request.amount == null || _request.to == null || _request.from == null)
            return
        convertCurrencyUseCase.invoke(_request).onEach {
            _convertCurrencyFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}