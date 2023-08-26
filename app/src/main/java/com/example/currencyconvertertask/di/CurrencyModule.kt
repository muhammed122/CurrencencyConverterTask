package com.example.currencyconvertertask.di


import com.example.currencyconvertertask.data.remote.CurrencyService
import com.example.currencyconvertertask.data.repository.CurrencyRepositoryImpl
import com.example.currencyconvertertask.domin.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {
    @Provides
    @Singleton
    fun provideCurrencyServices(
        retrofit: Retrofit
    ): CurrencyService = retrofit.create(CurrencyService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindCurrencyModule {
    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(
        CurrencyRepoImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}