package com.carlosherrera.presentation.ui.screens.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.carlosherrera.domain.model.Transaction

@ExperimentalMaterialApi
@Composable
fun TransactionListItem(
    transactionItem: Transaction,
) {
    Text(text = "${transactionItem.amount} - ${transactionItem.currency}")
}