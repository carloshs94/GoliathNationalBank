package com.carlosherrera.domain.currency

import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Transaction
import java.math.BigDecimal
import java.math.RoundingMode


class CurrencyConverter {

    private val outputCurrency = "EUR"

    var initialRates: List<Rate> = listOf()
    private val directRates = mutableMapOf<String, Rate>()

    fun setupRates(rates: List<Rate>) {
        initialRates = rates
        initialRates.filter { it.to == outputCurrency }.map { rate ->
            directRates[rate.from] = rate
        }
    }

    fun initRateConversion() {
        val directRate = directRates.values.first()
        getRates(directRate.from, directRate.rate)
    }

    private fun getRates(from: String, conversion: Double) {
        initialRates.filter { it.to == from && it.from != outputCurrency && directRates.containsKey(it.from).not() }.forEach {
            directRates[it.from] = Rate(it.from, outputCurrency, it.rate * conversion)
        }
        val currency = initialRates.firstOrNull { it.from != outputCurrency && directRates.containsKey(it.from).not() }
        currency?.let {
            getRates(currency.to, directRates[currency.to]!!.rate)
        }

    }

    fun calculateTransactionTotal(transactionList: List<Transaction>): BigDecimal {
        var amount = BigDecimal.ZERO
        transactionList.forEach { transaction ->
            val rate = directRates[transaction.currency]?.rate ?: 1.0
            rate?.let { rate ->
                amount += BigDecimal(rate).times(transaction.amount).setScale(2, RoundingMode.HALF_EVEN)
            }
        }
        return amount
    }

}