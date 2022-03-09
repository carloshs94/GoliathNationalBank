package com.carlosherrera.domain.usecases

import arrow.core.left
import arrow.core.right
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.domain.repository.TransactionRepository
import javax.inject.Inject

class GetProductsFromTransactionsUseCase @Inject constructor(private val transactionsRepository: TransactionRepository) {

    suspend operator fun invoke(): Result<List<Transaction>> =
        transactionsRepository.getTransactions().fold({ it.left() }) { transactions ->
            return transactions.distinctBy { it.sku }.right()
        }

}
