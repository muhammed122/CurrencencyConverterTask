package com.example.currencyconvertertask.network.network.adapter

import com.example.currencyconvertertask.network.model.GeneralErrorNetworkModel
import com.example.currencyconvertertask.utils.resource_provider.ResourceProvider
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiCallAdapterFactory(
    private val resourceProvider: ResourceProvider
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        check(returnType is ParameterizedType) {}
        val responseType = getParameterUpperBound(0, returnType)
        val errorBodyConverter = retrofit.responseBodyConverter<GeneralErrorNetworkModel>(
            GeneralErrorNetworkModel::class.java,
            annotations
        )

        return ApiResponseAdapter<Any>(responseType, resourceProvider, errorBodyConverter)

    }

}