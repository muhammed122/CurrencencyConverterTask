package com.example.currencyconvertertask.domin.entity.request

data class ConvertCurrencyRequest(
    val from: String,
    val to: String,
    val amount: Double,
)