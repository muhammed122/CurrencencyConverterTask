package com.example.currencyconvertertask.network.network

import com.example.currencyconvertertask.network.R
import com.example.currencyconvertertask.network.model.GeneralErrorNetworkModel
import com.example.currencyconvertertask.network.model.GeneralResponse
import com.example.currencyconvertertask.network.utils.ApiException
import com.example.currencyconvertertask.utils.resource_provider.ResourceProvider
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.*
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class NetworkResponseDelegate<T : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, GeneralErrorNetworkModel>,
    private val resourceProvider: ResourceProvider

) : Call<T> by delegate {

    override fun enqueue(callback: Callback<T>) = delegate.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful && response.body() is GeneralResponse &&
                (response.body() as GeneralResponse).success == true
            )
                callback.onResponse(
                    this@NetworkResponseDelegate, Response.success(response.body())
                )
            else {
                callback.onFailure(
                    this@NetworkResponseDelegate, handleApiException(
                        resourceProvider, response.code(), response.errorBody()
                    )
                )
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onFailure(this@NetworkResponseDelegate, parseApiException(t))

        }
    })

    private fun parseApiException(ex: Throwable): ApiException = when (ex) {
        is SocketTimeoutException,
        is ConnectException,
        is UnknownHostException,
        -> {
            ApiException.NoInternetConnection(
                message = resourceProvider.getText(R.string.no_internet_connection),
                throwable = ex
            )
        }
        is HttpException -> {
            handleApiException(
                resourceProvider,
                ex.code(),
                ex.response()?.errorBody()
            )
        }
        else -> ApiException.GeneralError(ex.message)
    }


    private fun handleApiException(
        resourceProvider: ResourceProvider,
        code: Int,
        errorBody: ResponseBody?,
    ): ApiException = when (code) {
        HttpURLConnection.HTTP_BAD_GATEWAY -> ApiException.GeneralError(
            resourceProvider.getText(R.string.error_server)
        )
        HttpURLConnection.HTTP_UNAUTHORIZED -> ApiException.Unauthorized

        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
            ApiException.TimeOut(resourceProvider.getText(R.string.error_server))
        }
        else -> {
            errorBody?.let {
                try {
                    val errorResponse =
                        errorConverter.convert(it)
                    ApiException.ApiError(
                        errorResponse?.message ?: resourceProvider.getText(R.string.error_server),
                        errorResponse?.code
                    )

                } catch (e: Exception) {
                    ApiException.ApiError()
                }

            } ?: ApiException.GeneralError(resourceProvider.getText(R.string.error_server))
        }

    }
}

