package com.example.currencyconvertertask.domin.entity.request

data class ConvertCurrencyRequest(
    var from: String?=null,
    var to: String?=null,
    var amount: Double?=null,
)