package com.example.currencyconvertertask.data.repository

import com.example.currencyconvertertask.config.EnvironmentConfig
import com.example.currencyconvertertask.data.model.response.SupportedCurrencyResponse
import com.example.currencyconvertertask.data.remote.CurrencyService
import com.example.currencyconvertertask.domin.entity.request.ConvertCurrencyRequest
import com.example.currencyconvertertask.domin.entity.response.CurrencyConverterModel
import com.example.currencyconvertertask.domin.entity.response.CurrencySymbolsModel
import com.example.currencyconvertertask.domin.entity.response.InfoModel
import com.example.currencyconvertertask.domin.entity.response.SymbolModel
import com.example.currencyconvertertask.domin.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val appConfig: EnvironmentConfig,
    private val service: CurrencyService
) : CurrencyRepository {
    override suspend fun getSymbols(): CurrencySymbolsModel {
        val response = service.getSymbols(
            accessKey = appConfig.getAPIKey()
        )

        return CurrencySymbolsModel(
            currencies = response?.symbols?.map {
                SymbolModel(
                    cur = it.key,
                    name = it.value
                )
            } ?: emptyList()
        )
    }

    override suspend fun convertCurrency(request: ConvertCurrencyRequest): CurrencyConverterModel {
        val response = service.convertCurrency(
            accessKey = appConfig.getAPIKey(),
            from = request.from ?: "",
            to = request.to ?: "",
            amount = request.amount ?: 0.0
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