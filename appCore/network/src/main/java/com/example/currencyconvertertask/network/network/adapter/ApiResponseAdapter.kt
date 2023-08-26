package com.example.currencyconvertertask.network.network.adapter

import com.example.currencyconvertertask.network.model.GeneralErrorNetworkModel
import com.example.currencyconvertertask.network.network.NetworkResponseDelegate
import com.example.currencyconvertertask.utils.resource_provider.ResourceProvider
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class ApiResponseAdapter<T : Any>(
    private val successType: Type,
    private val resourceProvider: ResourceProvider,
    private val errorConverter: Converter<ResponseBody, GeneralErrorNetworkModel>
) : CallAdapter<T, Call<T>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<T> =
        NetworkResponseDelegate(call, errorConverter, resourceProvider)
}