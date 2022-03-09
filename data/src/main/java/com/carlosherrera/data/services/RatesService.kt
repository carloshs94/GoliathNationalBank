package com.carlosherrera.data.services

import com.carlosherrera.data.model.RateDTO
import retrofit2.Response
import retrofit2.http.GET

interface RatesService {
    @GET("/rates")
    suspend fun getRates(): List<RateDTO>
}