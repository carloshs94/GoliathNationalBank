package com.carlosherrera.data.model

import com.carlosherrera.domain.model.Rate
import java.math.BigDecimal

data class RateDTO(
    val from: String,
    val to: String,
    val rate: Double
)

fun RateDTO.toDomain() = Rate(from, to, rate)