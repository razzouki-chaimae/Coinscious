package com.chaimaerazzouki.coinscious.dashboard.presentation

import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.coinscious.core.base.BaseViewModel
import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.dashboard.domain.enums.DashboardEffect
import com.chaimaerazzouki.coinscious.dashboard.domain.enums.DashboardEvent
import com.chaimaerazzouki.coinscious.dashboard.domain.model.CategorySpending
import com.chaimaerazzouki.coinscious.dashboard.domain.model.DailySpending
import com.chaimaerazzouki.coinscious.dashboard.domain.model.DashboardData
import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category
import com.chaimaerazzouki.coinscious.transactions.domain.enums.TransactionType
import com.chaimaerazzouki.coinscious.transactions.domain.model.Transaction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

class DashboardViewModel() : BaseViewModel<DashboardState, DashboardEvent, DashboardEffect>(
    initialState = DashboardState()
) {

    init {
        loadDashboardData()
    }

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.Refresh -> loadDashboardData()
            is DashboardEvent.AddExpenseClick -> sendEffect(DashboardEffect.NavigateToAddExpense)
            is DashboardEvent.AddIncomeClick -> sendEffect(DashboardEffect.NavigateToAddIncome)
            is DashboardEvent.TransactionClick -> sendEffect(
                DashboardEffect.NavigateToTransactionDetail(event.transactionId)
            )
            is DashboardEvent.ViewAllTransactionsClick -> sendEffect(
                DashboardEffect.NavigateToAllTransactions
            )
        }
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }

            try {
                delay(600)
                val data = createMockData()
                setState { copy(isLoading = false, dashboardData = data, error = null) }

            } catch (e: Exception) {
                setState { copy(isLoading = false, error = e.message) }
            }
        }
    }

    // TODO: To be replaced by remote data
    private fun createMockData(): DashboardData {
        val today = LocalDate.now()
        val weekData = buildLast7Days(today)

        return DashboardData(
            monthlyGoal = Money(2000.00),
            spentSoFar = Money(1450.75),
            remaining = Money(549.25),
            dailyAverage = Money(48.36),
            daysUntilReset = 12,
            recentTransactions = createMockTransactions(),
            weeklySpending = weekData,
            topCategories = listOf(
                CategorySpending(Category.FOOD, Money(450.00), 0.31f, "🍔"),
                CategorySpending(Category.SHOPPING, Money(320.00), 0.22f, "🛍️"),
                CategorySpending(Category.BILLS, Money(280.00), 0.19f, "📄")
            )
        )
    }

    // TODO: To be replaced by remote data
    private fun buildLast7Days(today: LocalDate): List<DailySpending> {
        val result = mutableListOf<DailySpending>()

        for (i in 6 downTo 0) {
            val day = today.minusDays(i.toLong())
            val isToday = i == 0

            val shortName = when (day.dayOfWeek) {
                java.time.DayOfWeek.MONDAY -> "M"
                java.time.DayOfWeek.TUESDAY -> "T"
                java.time.DayOfWeek.WEDNESDAY -> "W"
                java.time.DayOfWeek.THURSDAY -> "T"
                java.time.DayOfWeek.FRIDAY -> "F"
                java.time.DayOfWeek.SATURDAY -> "S"
                java.time.DayOfWeek.SUNDAY -> "S"
            }

            val amount = Money(
                when {
                    isToday -> 35.00
                    else -> Random.nextDouble(15.0, 120.0)
                }
            )

            result.add(
                DailySpending(
                    dayOfWeek = shortName,
                    amount = amount,
                    isToday = isToday
                )
            )
        }

        return result
    }

    // TODO: To be replaced by remote data
    private fun createMockTransactions(): List<Transaction> {
        return listOf(
            Transaction(
                id = "1",
                amount = Money(45.50),
                category = Category.FOOD,
                note = "Lunch with team",
                date = kotlinx.datetime.LocalDateTime(2026, 1, 1, 12, 0),
                type = TransactionType.EXPENSE
            ),
            Transaction(
                id = "2",
                amount = Money(1200.00),
                category = Category.SALARY,
                note = "Monthly paycheck",
                date = kotlinx.datetime.LocalDateTime(2026, 1, 1, 9, 0),
                type = TransactionType.INCOME
            ),
            Transaction(
                id = "3",
                amount = Money(35.00),
                category = Category.TRANSPORT,
                note = "Gas refill",
                date = kotlinx.datetime.LocalDateTime(2026, 1, 1, 18, 0),
                type = TransactionType.EXPENSE
            )
        )
    }
}