package com.carlosherrera.goliathnationalbank.di

import com.carlosherrera.domain.currency.CurrencyConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelperProvider {

    @Singleton
    @Provides
    fun provideCurrencyConverter(): CurrencyConverter = CurrencyConverter()
}