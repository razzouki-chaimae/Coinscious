package com.chaimaerazzouki.database.di

import androidx.room.Room
import com.chaimaerazzouki.database.CoinsciousDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            CoinsciousDatabase::class.java,
            "coinscious.db"
        ).build()
    }

    single {
        get<CoinsciousDatabase>().transactionDao()
    }
}