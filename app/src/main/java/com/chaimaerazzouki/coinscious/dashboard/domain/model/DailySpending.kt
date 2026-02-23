package com.chaimaerazzouki.coinscious.dashboard.domain.model

import com.chaimaerazzouki.coinscious.core.utils.Money

data class DailySpending(
    val dayOfWeek: String, // "M", "T", "W", etc.
    val amount: Money,
    val isToday: Boolean = false
)