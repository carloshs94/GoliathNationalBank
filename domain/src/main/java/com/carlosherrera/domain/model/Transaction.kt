package com.carlosherrera.domain.model

import java.math.BigDecimal

data class Transaction(
    val sku: String,
    val amount: BigDecimal,
    val currency: String
)