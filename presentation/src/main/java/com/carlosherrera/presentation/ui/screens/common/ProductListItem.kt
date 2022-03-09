package com.carlosherrera.presentation.ui.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosherrera.domain.model.Transaction

@ExperimentalMaterialApi
@Composable
fun <T : Transaction> ProductListItem(
    transactionItem: T,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Card(elevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
            Text(
                text = transactionItem.sku,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}