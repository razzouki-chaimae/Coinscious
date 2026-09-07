package com.chaimaerazzouki.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chaimaerazzouki.database.dao.TransactionDao
import com.chaimaerazzouki.database.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CoinsciousDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}