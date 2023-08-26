package com.example.currencyconvertertask.utils.di

import com.example.currencyconvertertask.utils.coroutine_dispatcher.DefaultDispatcher
import com.example.currencyconvertertask.utils.coroutine_dispatcher.IoDispatcher
import com.example.currencyconvertertask.utils.coroutine_dispatcher.MainDispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

  @Provides
  @DefaultDispatcher
  fun providesDefaultDispatcher() = Dispatchers.Default

  @Provides
  @IoDispatcher
  fun providesIoDispatcher() = Dispatchers.IO

  @Provides
  @MainDispatcher
  fun providesMainDispatcher() = Dispatchers.Main
}