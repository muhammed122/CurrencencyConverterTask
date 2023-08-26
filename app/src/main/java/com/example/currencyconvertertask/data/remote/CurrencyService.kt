package com.example.currencyconvertertask.data.remote

import com.example.currencyconvertertask.data.model.response.CurrencyConverterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("convert")
    suspend fun convertCurrency(
        @Query("access_key") accessKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double,
    ): CurrencyConverterResponse?
}