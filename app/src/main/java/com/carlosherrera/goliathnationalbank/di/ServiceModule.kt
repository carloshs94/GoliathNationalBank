package com.carlosherrera.goliathnationalbank.di

import com.carlosherrera.data.datasource.RemoteDataSourceImpl
import com.carlosherrera.data.services.RatesService
import com.carlosherrera.data.services.TransactionsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideRatesService(retrofit: Retrofit): RatesService = retrofit.create(RatesService::class.java)

    @Singleton
    @Provides
    fun provideTransactionsService(retrofit: Retrofit): TransactionsService = retrofit.create(TransactionsService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(ratesService: RatesService, transactionsService: TransactionsService): RemoteDataSourceImpl =
        RemoteDataSourceImpl(ratesService, transactionsService)

}