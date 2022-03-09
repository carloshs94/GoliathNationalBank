package com.carlosherrera.domain.repository

import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Result

interface RatesRepository {
    suspend fun getRates(): Result<List<Rate>>
}