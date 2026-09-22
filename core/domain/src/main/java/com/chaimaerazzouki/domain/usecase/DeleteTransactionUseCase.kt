package com.chaimaerazzouki.domain.usecase

import com.chaimaerazzouki.model.Transaction
import com.chaimaerazzouki.repository.TransactionRepository

class DeleteTransactionUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.deleteTransaction(transaction)
    }
}