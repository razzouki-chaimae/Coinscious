package com.chaimaerazzouki.coinscious.dashboard.domain.enums

import com.chaimaerazzouki.coinscious.core.base.UiEffect

sealed class DashboardEffect : UiEffect {
    object NavigateToAddExpense : DashboardEffect()
    object NavigateToAddIncome : DashboardEffect()
    data class NavigateToTransactionDetail(val transactionId: String) : DashboardEffect()
    object NavigateToAllTransactions : DashboardEffect()
}