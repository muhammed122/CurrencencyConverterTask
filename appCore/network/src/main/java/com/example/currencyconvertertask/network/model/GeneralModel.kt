package com.example.currencyconvertertask.network.model

import com.google.gson.annotations.SerializedName

open class GeneralResponse(
    @SerializedName("success") val success: Boolean? = null,
    )
