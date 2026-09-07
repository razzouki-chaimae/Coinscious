package com.chaimaerazzouki.database.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val amount: Double,
    val merchant: String,
    val categoryId: String,
    val timestamp: Long,
    val isAutoCaptured: Boolean
)