package com.carlosherrera.presentation.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.left
import arrow.core.right
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.model.Transaction
import com.carlosherrera.domain.usecases.GetConvertedTransactionTotal
import com.carlosherrera.domain.usecases.GetProductTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductTransactionsUseCase: GetProductTransactionsUseCase,
    private val getConvertedTransactionTotal: GetConvertedTransactionTotal
) : ViewModel() {

    private val _state = MutableStateFlow(UiState(loading = true))
    val state = _state.asStateFlow()

    fun getFilteredList(sku: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val transactionResult = getProductTransactionsUseCase(sku)
                _state.update {
                    it.copy(loading = false,
                        transactions = transactionResult.fold({ it.left() }) { list ->
                            list.filter { it.sku == sku }.right()
                        }
                    )
                }
                delay(1000)
                val total = transactionResult.fold({ null }) {
                    getConvertedTransactionTotal(it)
                }
                _state.update {
                    it.copy(calculatedTotal = total)
                }
            }

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val transactions: Result<List<Transaction>> = emptyList<Transaction>().right(),
        val calculatedTotal: BigDecimal? = null
    )
}