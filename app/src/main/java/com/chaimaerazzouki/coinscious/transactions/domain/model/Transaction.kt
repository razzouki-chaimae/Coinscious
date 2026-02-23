package com.chaimaerazzouki.coinscious.transactions.domain.model

import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category
import com.chaimaerazzouki.coinscious.transactions.domain.enums.TransactionType
import kotlinx.datetime.LocalDateTime

data class Transaction(
    val id: String,
    val amount: Money,
    val category: Category,
    val note: String,
    val date: LocalDateTime,
    val type: TransactionType
)
