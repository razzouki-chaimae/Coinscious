package com.chaimaerazzouki.mapper

import com.chaimaerazzouki.database.entity.TransactionEntity
import com.chaimaerazzouki.model.Transaction
import java.time.Instant

fun TransactionEntity.toModel(): Transaction {
    return Transaction(
        id = id,
        amount = amount,
        merchant = merchant,
        categoryId = categoryId,
        timestamp = Instant.ofEpochMilli(timestamp),
        isAutoCaptured = isAutoCaptured
    )
}

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        amount = amount,
        merchant = merchant,
        categoryId = categoryId,
        timestamp = timestamp.toEpochMilli(),
        isAutoCaptured = isAutoCaptured
    )
}