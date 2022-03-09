package com.carlosherrera.data.datasource

import arrow.core.left
import arrow.core.right
import com.carlosherrera.data.model.toDomain
import com.carlosherrera.data.services.RatesService
import com.carlosherrera.data.services.TransactionsService
import com.carlosherrera.domain.datasource.RemoteDataSource
import com.carlosherrera.domain.model.toError

class RemoteDataSourceImpl(
    private val ratesService: RatesService,
    private val transactionsService: TransactionsService
) : RemoteDataSource {
    override suspend fun getRates() = try {
        ratesService.getRates().map { it.toDomain() }.right()
    } catch (e: Exception) {
        e.toError().left()
    }

    override suspend fun getTransactions() = try {
        transactionsService.getTransactions().map { it.toDomain() }.right()
    } catch (e: Exception) {
        e.toError().left()
    }

}