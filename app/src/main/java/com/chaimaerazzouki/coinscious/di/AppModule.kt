package com.chaimaerazzouki.coinscious.di

import com.chaimaerazzouki.database.di.databaseModule
import com.chaimaerazzouki.di.dataModule
import org.koin.dsl.module

// Koin modules aggregation
val appModule = module {

    // ViewModels
    //viewModel { DashboardViewModel() }
    //viewModel { AddTransactionViewModel(application = get()) }

    // Add more dependencies here as your app grows
    // Example:
    // single { TransactionRepositoryImpl(get()) as TransactionRepository }
    // single { GetTransactionsUseCase(get()) }
}

val coinsciousModules = listOf(
    appModule,
    databaseModule,
    dataModule,
    // dashboardModule,
    // quickLogModule,
    // budgetsModule,
    // settingsModule,
)