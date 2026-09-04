package com.chaimaerazzouki.model

import java.time.Instant

data class Transaction(
    val id: String,
    val amount: Double,
    val merchant: String,
    val categoryId: String,
    val timestamp: Instant,
    val isAutoCaptured: Boolean
)
