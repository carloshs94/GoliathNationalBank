package com.carlosherrera.presentation.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.presentation.ui.screens.common.TransactionListItem

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DetailScreen(
    sku: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.getFilteredList(sku)
    val state by viewModel.state.collectAsState()
    Column {
        Text(
            text = "SKU: $sku", style = MaterialTheme.typography.h4, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), textAlign = TextAlign.Center
        )
        DetailItemsListScreen(loading = state.loading, transactions = state.transactions)
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            elevation = 4.dp, shape = RoundedCornerShape(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (state.calculatedTotal == null) {
                    CircularProgressIndicator(modifier = Modifier.padding(10.dp))
                    Text("Calculando total...", style = MaterialTheme.typography.h5)
                } else
                    Text("Total ${state.calculatedTotal}", style = MaterialTheme.typography.h5)


            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DetailItemsListScreen(
    loading: Boolean = false,
    transactions: Result<List<Transaction>>
) {
    if (loading)
        CircularProgressIndicator()
    transactions.fold({ Text(text = "Error recuperando") }) {
        Log.d("PRUEBA", "BIEN CARGADO" + it.size)
        TransactionsItemsList(it)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun TransactionsItemsList(
    transactions: List<Transaction>
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (transactions.isNotEmpty()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp),
                cells = GridCells.Fixed(4),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(transactions) {
                    TransactionListItem(transactionItem = it)
                }
            }
        }
    }
}