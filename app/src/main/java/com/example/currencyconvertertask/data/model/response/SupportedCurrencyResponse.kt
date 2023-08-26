package com.example.currencyconvertertask.data.model.response

import com.example.currencyconvertertask.network.model.GeneralResponse
import com.google.gson.annotations.SerializedName

data class SupportedCurrencyResponse(
	@SerializedName("symbols") val symbols: Map<String,String>? = null
): GeneralResponse()


