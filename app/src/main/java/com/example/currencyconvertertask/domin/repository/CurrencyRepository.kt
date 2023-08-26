package com.example.currencyconvertertask.domin.repository

import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel

interface CurrencyRepository {

    suspend fun convertCurrency(request: ConvertCurrencyRequest):CurrencyConverterModel
}