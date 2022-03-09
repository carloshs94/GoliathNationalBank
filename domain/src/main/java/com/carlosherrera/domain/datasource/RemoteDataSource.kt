package com.carlosherrera.domain.datasource

import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction

interface RemoteDataSource {
    suspend fun getRates(): Result<List<Rate>>
    suspend fun getTransactions(): Result<List<Transaction>>
}