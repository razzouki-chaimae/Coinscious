package com.chaimaerazzouki.coinscious.dashboard.domain.enums

import com.chaimaerazzouki.coinscious.core.base.UiEvent

sealed class DashboardEvent : UiEvent {
    object Refresh : DashboardEvent()
    object AddExpenseClick : DashboardEvent()
    object AddIncomeClick : DashboardEvent()
    data class TransactionClick(val transactionId: String) : DashboardEvent()
    object ViewAllTransactionsClick : DashboardEvent()
}