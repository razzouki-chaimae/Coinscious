package com.chaimaerazzouki.repository

import com.chaimaerazzouki.database.dao.TransactionDao
import com.chaimaerazzouki.mapper.toEntity
import com.chaimaerazzouki.mapper.toModel
import com.chaimaerazzouki.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao
            .getTransactions()
            .map { transactions ->
                transactions.map { it.toModel() }
            }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(
            transaction.toEntity()
        )
    }
}