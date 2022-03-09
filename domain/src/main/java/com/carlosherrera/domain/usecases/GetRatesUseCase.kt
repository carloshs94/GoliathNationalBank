package com.carlosherrera.domain.usecases

import arrow.core.left
import arrow.core.right
import com.carlosherrera.domain.currency.CurrencyConverter
import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.repository.RatesRepository
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val ratesRepository: RatesRepository, private val currencyConverter: CurrencyConverter) {

    suspend operator fun invoke(): Result<List<Rate>> =
        ratesRepository.getRates().fold({ it.left() }) { list ->
            currencyConverter.run {
                setupRates(list)
                initRateConversion()
            }
            list.right()
        }
}