package com.example.currencyconvertertask.data.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyConverterResponse(
	@SerializedName("date") val date: String? = null,
	@SerializedName("result") val result: Any? = null,
	@SerializedName("success") val success: Boolean? = null,
	@SerializedName("query") val query: Query? = null,
	@SerializedName("historical") val historical: String? = null,
	@SerializedName("info") val info: Info? = null
)

data class Query(
	@SerializedName("amount") val amount: Int? = null,
	@SerializedName("from") val from: String? = null,
	@SerializedName("to") val to: String? = null
)

data class Info(
	@SerializedName("rate") val rate: Any? = null,
	@SerializedName("timestamp") val timestamp: Int? = null
)
