package com.carlosherrera.presentation.ui.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.presentation.ui.screens.common.ErrorMessage
import com.carlosherrera.presentation.ui.screens.common.ProductListItem


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ProductsScreen(
    onClick: (Transaction) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ProductsItemsListScreen(loading = state.loading, items = state.products, onClick = onClick)
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ProductsItemsListScreen(
    loading: Boolean = false,
    items: Result<List<Transaction>>,
    onClick: (Transaction) -> Unit
) {
    items.fold({ ErrorMessage(it) }) { products ->
        ProductsItemsList(loading = loading, products = products, onClick = onClick)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ProductsItemsList(
    loading: Boolean,
    products: List<Transaction>,
    onClick: (Transaction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        }
        if (products.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(24.dp),
            ) {
                items(products) {
                    ProductListItem(transactionItem = it, modifier = Modifier
                        .clickable { onClick(it) }
                        .padding(12.dp))
                }
            }
        }
    }
}