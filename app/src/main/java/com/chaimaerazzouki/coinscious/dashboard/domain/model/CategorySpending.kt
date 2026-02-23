package com.chaimaerazzouki.coinscious.dashboard.domain.model

import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category

data class CategorySpending(
    val category: Category,
    val amount: Money,
    val percentage: Float,
    val emoji: String
)