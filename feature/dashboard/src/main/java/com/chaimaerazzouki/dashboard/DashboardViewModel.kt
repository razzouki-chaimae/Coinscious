package com.chaimaerazzouki.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.domain.usecase.GetTransactionUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    private val getTransactionsUseCase: GetTransactionUseCase
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> =
        getTransactionsUseCase()
            .map { transactions ->
                DashboardUiState.Success(
                    safeToSpendToday = 50000.0, //calculateSafeToSpend(transactions),
                    safeToSpendProgress = 90f, //calculateProgress(transactions),
                    recentTransactions = transactions.take(4)
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DashboardUiState.Loading
            )

    // TODO: TO BE IMPLEMENTED
//    private fun calculateSafeToSpend(
//        transactions: List<Transaction>
//    ): Double {
//        // Dashboard business calculation
//    }
//
//    private fun calculateProgress(
//        transactions: List<Transaction>
//    ): Float {
//        // Dashboard business calculation
//    }

}