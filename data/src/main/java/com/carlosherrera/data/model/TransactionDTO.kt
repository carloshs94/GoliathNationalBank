package com.carlosherrera.data.model

import com.carlosherrera.domain.model.Transaction
import java.math.BigDecimal


data class TransactionDTO(
    val sku: String,
    val amount: String,
    val currency: String
)

fun TransactionDTO.toDomain() = Transaction(sku, BigDecimal(amount), currency)