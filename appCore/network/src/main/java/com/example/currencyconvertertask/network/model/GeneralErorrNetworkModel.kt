package com.example.currencyconvertertask.network.model

data class GeneralErrorNetworkModel(
    val code: String?=null,
    val message: String?=null,
    val endPoint: String?=null
)