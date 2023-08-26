package com.example.currencyconvertertask.utils.di

import com.example.currencyconvertertask.utils.resource_provider.IResourceProvider
import com.example.currencyconvertertask.utils.resource_provider.ResourceProvider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {
    @Binds
    abstract fun bindResourceProvider(resourceProvider: ResourceProvider): IResourceProvider
}