package com.chaimaerazzouki.di

import com.chaimaerazzouki.repository.TransactionRepository
import com.chaimaerazzouki.repository.TransactionRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<TransactionRepository> {
        TransactionRepositoryImpl(
            transactionDao = get()
        )
    }
}