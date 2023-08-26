package com.example.currencyconvertertask.config

import javax.inject.Inject

class AppEnvironment @Inject constructor() : EnvironmentConfig {


    override fun getBaseUrl(): String = BuildConfig.BASE_URL


    override fun getAPI(): String = BuildConfig.API_KEY


}