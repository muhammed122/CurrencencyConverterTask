package com.example.currencyconvertertask.domin.entity.response

data class CurrencySymbolsModel(val currencies: List<SymbolModel>)

data class SymbolModel(
    val cur: String,
    val name: String
)