package com.example.currencyconvertertask.data.model.response

import com.google.gson.annotations.SerializedName

data class SupportedCurrencyResponse(
	@SerializedName("success") val success: Boolean? = null,
	@SerializedName("symbols") val symbols: Map<String,String>? = null
)


