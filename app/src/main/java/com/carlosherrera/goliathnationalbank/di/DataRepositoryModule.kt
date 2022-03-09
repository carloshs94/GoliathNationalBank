package com.carlosherrera.goliathnationalbank.di

import com.carlosherrera.data.repository.RatesRepositoryImpl
import com.carlosherrera.data.repository.TransactionsRepositoryImpl
import com.carlosherrera.domain.repository.RatesRepository
import com.carlosherrera.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataRepositoryModule {

    @Binds
    internal abstract fun bindRatesRepository(ratesRepositoryImpl: RatesRepositoryImpl): RatesRepository

    @Binds
    internal abstract fun bindTransactionsRepository(transactionsRepositoryImpl: TransactionsRepositoryImpl): TransactionRepository
}