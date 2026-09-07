package com.chaimaerazzouki.repository

import com.chaimaerazzouki.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getTransactions(): Flow<List<Transaction>>

    suspend fun addTransaction(transaction: Transaction)

}