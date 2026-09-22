package com.chaimaerazzouki.domain.usecase

import com.chaimaerazzouki.model.Transaction
import com.chaimaerazzouki.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionUseCase(
    private val repository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getTransactions()
    }
}