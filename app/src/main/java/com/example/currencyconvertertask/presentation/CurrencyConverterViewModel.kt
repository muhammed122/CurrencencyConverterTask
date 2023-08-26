package com.example.currencyconvertertask.presentation

import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.usecase.ConvertCurrencyUseCase
import com.example.currencyconvertertask.ui.base.BaseViewModel
import com.qpn.kamashka.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : BaseViewModel() {

    private val _convertCurrencyFlow = MutableSharedFlow<Resource<CurrencyConverterModel>>()
    private val convertCurrencyFlow get() = _convertCurrencyFlow

    fun convertCurrency(request: ConvertCurrencyRequest) {
        convertCurrencyUseCase.invoke(request).onEach {
            _convertCurrencyFlow.emit(it)
        }
    }
}