package com.example.currencyconvertertask.domin.repository

import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.entity.response.CurrencySymbolsModel

interface CurrencyRepository {

    suspend fun getSymbols(): CurrencySymbolsModel?
    suspend fun convertCurrency(request: ConvertCurrencyRequest):CurrencyConverterModel
}