package com.carlosherrera.presentation.ui.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.domain.usecases.GetProductsFromTransactionsUseCase
import com.carlosherrera.domain.usecases.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsFromTransactionsUseCase: GetProductsFromTransactionsUseCase,
    private val getRatesUseCase: GetRatesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UIState(loading = true))
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val resultProducts = getProductsFromTransactionsUseCase()
                getRatesUseCase()
                _state.update {
                    it.copy(loading = false, products = resultProducts)//, rates = resultRates)
                }
            }
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val products: Result<List<Transaction>> = emptyList<Transaction>().right(),
    )
}