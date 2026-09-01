package com.chaimaerazzouki.coinscious.di

import com.chaimaerazzouki.coinscious.dashboard.presentation.DashboardViewModel
import com.chaimaerazzouki.coinscious.transactions.presentation.add.AddTransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// Koin modules aggregation
val appModule = module {

    // ViewModels
    viewModel { DashboardViewModel() }
    viewModel { AddTransactionViewModel(application = get()) }

    // Add more dependencies here as your app grows
    // Example:
    // single { TransactionRepositoryImpl(get()) as TransactionRepository }
    // single { GetTransactionsUseCase(get()) }
}