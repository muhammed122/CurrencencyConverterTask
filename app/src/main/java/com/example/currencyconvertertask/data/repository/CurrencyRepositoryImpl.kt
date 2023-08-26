package com.example.currencyconvertertask.data.repository

import com.example.currencyconvertertask.config.EnvironmentConfig
import com.example.currencyconvertertask.data.remote.CurrencyService
import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.entity.response.InfoModel
import com.example.currencyconvertertask.domin.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val appConfig: EnvironmentConfig,
    private val service: CurrencyService
) : CurrencyRepository {
    override suspend fun convertCurrency(request: ConvertCurrencyRequest): CurrencyConverterModel {
        val response = service.convertCurrency(
            accessKey = appConfig.getAPIKey(),
            from = request.from,
            to = request.to,
            amount = request.amount
        )

        return CurrencyConverterModel(
            date = response?.date,
            result = response?.result,
            success = response?.success,
            info = InfoModel(
                rate = response?.info?.rate,
                timestamp = response?.info?.timestamp
            )
        )
    }
}