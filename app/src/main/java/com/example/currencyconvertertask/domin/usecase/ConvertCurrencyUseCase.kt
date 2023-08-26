package com.example.currencyconvertertask.domin.usecase

import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.repository.CurrencyRepository
import com.example.currencyconvertertask.utils.usecase.FlowUseCase
import com.example.currencyconvertertask.utils.coroutine_dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<ConvertCurrencyRequest, CurrencyConverterModel>(ioDispatcher) {
    override fun execute(parameters: ConvertCurrencyRequest): Flow<CurrencyConverterModel> = flow {


        emit(repository.convertCurrency(parameters))
    }
}