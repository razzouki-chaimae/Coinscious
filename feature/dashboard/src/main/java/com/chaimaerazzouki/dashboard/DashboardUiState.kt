package com.chaimaerazzouki.dashboard

import com.chaimaerazzouki.model.Transaction

sealed interface DashboardUiState {

    data object Loading : DashboardUiState

    data class Success(
        val safeToSpendToday: Double,
        val safeToSpendProgress: Float,
        val recentTransactions: List<Transaction>
    ) : DashboardUiState

    data class Error(
        val message: String
    ) : DashboardUiState
}
