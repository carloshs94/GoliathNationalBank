package com.carlosherrera.domain

import com.carlosherrera.domain.currency.CurrencyConverter
import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Transaction
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverterTest {
    lateinit var currencyConverter: CurrencyConverter

    private val rates = listOf(
        Rate("EUR", "USD", 1.359),
        Rate("CAD", "EUR", 0.732),
        Rate("USD", "EUR", 0.736),
        Rate("EUR", "CAD", 1.366),
    )

    private val exampleProductTransaction = listOf(
        Transaction("T2006", BigDecimal(10), "USD"),
        Transaction("T2006", BigDecimal(7.63), "EUR"),
        )

    @Before
    fun currencyConverterSetup(){
        currencyConverter = CurrencyConverter()
        currencyConverter.apply{
            setupRates(rates)
            currencyConverter.initRateConversion()
        }
    }

    @Test
    fun `calculate total amount of exampleTransaction`(){
        val calculatedTotal = currencyConverter.calculateTransactionTotal(exampleProductTransaction)
        val expectedTotal = BigDecimal(14.99).setScale(2, RoundingMode.HALF_EVEN)
        assert(calculatedTotal == expectedTotal)
    }

}