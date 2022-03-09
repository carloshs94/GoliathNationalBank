package com.carlosherrera.data.repository

import arrow.core.left
import arrow.core.right
import com.carlosherrera.data.datasource.RemoteDataSourceImpl
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.domain.repository.Repository
import com.carlosherrera.domain.repository.TransactionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsRepositoryImpl @Inject constructor(
    private var remoteDataSourceImpl: RemoteDataSourceImpl
) : TransactionRepository, Repository<Transaction>() {

    override suspend fun getTransactions(): Result<List<Transaction>> {
        if (cache.isEmpty())
            cache = remoteDataSourceImpl.getTransactions().fold({
                return it.left()
            }) { it }
        return cache.right()
    }
}