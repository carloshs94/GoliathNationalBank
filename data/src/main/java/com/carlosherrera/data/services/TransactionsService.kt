package com.carlosherrera.data.services

import com.carlosherrera.data.model.TransactionDTO
import retrofit2.Response
import retrofit2.http.GET

interface TransactionsService {
    @GET("/transactions")
    suspend fun getTransactions(): List<TransactionDTO>
}