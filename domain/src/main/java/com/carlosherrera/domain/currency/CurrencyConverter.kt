package com.carlosherrera.domain.currency

import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Transaction
import java.math.BigDecimal
import java.math.RoundingMode


class CurrencyConverter {

    private val outputCurrency = "EUR"

    private var initialRates: List<Rate> = listOf()
    private val directRates = mutableMapOf<String, Rate>()

    fun setupRates(rates: List<Rate>) {
        initialRates = rates
        directRates[outputCurrency] = Rate(outputCurrency, outputCurrency, 1.0) // same currency ratio

        initialRates.filter { it.to == outputCurrency }.map { rate ->
            directRates[rate.from] = rate
        }
    }

    fun initRateConversion() {
        val directRate = directRates.values.first { it.from != outputCurrency }
        getRates(directRate.from, directRate.rate)
    }

    private fun getRates(convertFrom: String, conversionRate: Double) {
        initialRates.filter { filteredRate ->
            filteredRate.to == convertFrom && directRates.containsKey(filteredRate.from).not()   //Filter the given rates with conversion available within another currency in the list
        }.forEach {
            directRates[it.from] = Rate(it.from, outputCurrency, it.rate * conversionRate)  //The rates given can be added to the direct rates list
        }
        val nextCurrencyToUse = initialRates.firstOrNull { directRates.containsKey(it.from).not() }  //Check for any currency that can be used to convert any other currency left

        nextCurrencyToUse?.let { currency ->
            directRates[currency.to]?.rate?.let { directRate ->
                getRates(currency.to, directRate)  //Recursive call
            }
        }

    }

    fun calculateTransactionTotal(transactionList: List<Transaction>): BigDecimal {
        var amount = BigDecimal.ZERO
        transactionList.forEach { transaction ->
            val rate = directRates[transaction.currency]?.rate ?: 1.0
            amount += BigDecimal(rate).times(transaction.amount).setScale(2, RoundingMode.HALF_EVEN)
        }
        return amount
    }

}