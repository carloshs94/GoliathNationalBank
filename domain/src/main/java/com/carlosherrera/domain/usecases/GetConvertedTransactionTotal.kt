package com.carlosherrera.domain.usecases

import com.carlosherrera.domain.currency.CurrencyConverter
import com.carlosherrera.domain.model.Transaction
import java.math.BigDecimal
import javax.inject.Inject

class GetConvertedTransactionTotal @Inject constructor(
    private val currencyConverter: CurrencyConverter
) {
    operator fun invoke(transactionList: List<Transaction>): BigDecimal = currencyConverter.calculateTransactionTotal(transactionList)

}