package com.carlosherrera.domain.repository

import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction

interface TransactionRepository {
    suspend fun getTransactions(): Result<List<Transaction>>
}