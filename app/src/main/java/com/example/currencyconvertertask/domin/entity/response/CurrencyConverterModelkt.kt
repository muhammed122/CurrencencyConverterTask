package com.example.currencyconvertertask.domin.entity.response

import com.google.gson.annotations.SerializedName

data class CurrencyConverterModel(
    val date: String? = null,
    val result: Double? = null,
    val success: Boolean? = null,
    val historical: String? = null,
    val info: InfoModel? = null
)


data class InfoModel(
    val rate: Any? = null,
    val timestamp: Int? = null
)
