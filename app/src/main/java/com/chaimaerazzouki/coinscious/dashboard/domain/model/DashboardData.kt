package com.chaimaerazzouki.coinscious.dashboard.domain.model

import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.transactions.domain.model.Transaction

data class DashboardData(
    val monthlyGoal: Money,
    val spentSoFar: Money,
    val remaining: Money,
    val dailyAverage: Money,
    val daysUntilReset: Int,
    val recentTransactions: List<Transaction>,
    val weeklySpending: List<DailySpending>,
    val topCategories: List<CategorySpending>
) {
    val progressPercentage: Float
        get() = if (monthlyGoal.value > 0) {
            (spentSoFar.value / monthlyGoal.value).coerceIn(0.0, 1.0).toFloat()
        } else 0f

    val isOnTrack: Boolean
        get() = progressPercentage < 0.75f

    val isNearingLimit: Boolean
        get() = progressPercentage in 0.75f..0.9f

    val isOverBudget: Boolean
        get() = spentSoFar > monthlyGoal
}