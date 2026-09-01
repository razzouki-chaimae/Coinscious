package com.chaimaerazzouki.coinscious.core.utils

import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category

val Category.emoji: String
    get() = when (this) {
        Category.Food -> "🍔"
        Category.Transport -> "🚗"
        Category.Shopping -> "🛍️"
        Category.Entertainment -> "🎬"
        Category.Bills -> "📄"
        Category.Health -> "💊"
        Category.Education -> "📚"
        Category.Salary -> "💰"
        Category.Investment -> "📈"
        Category.Other -> "📦"
    }