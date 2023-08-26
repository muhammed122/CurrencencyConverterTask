package com.example.currencyconvertertask.config.di

import com.example.currencyconvertertask.config.AppEnvironment
import com.example.currencyconvertertask.config.EnvironmentConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigModule {

    @Binds
    @Singleton
    abstract fun getAppEnvironment(
        appEnvironment: AppEnvironment
    ): EnvironmentConfig
}