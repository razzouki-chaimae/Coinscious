package com.chaimaerazzouki.coinscious.core.utils

import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category

val Category.emoji: String
    get() = when (this) {
        Category.FOOD -> "🍔"
        Category.TRANSPORT -> "🚗"
        Category.SHOPPING -> "🛍️"
        Category.ENTERTAINMENT -> "🎬"
        Category.BILLS -> "📄"
        Category.HEALTH -> "💊"
        Category.EDUCATION -> "📚"
        Category.SALARY -> "💰"
        Category.INVESTMENT -> "📈"
        Category.OTHER -> "📦"
    }