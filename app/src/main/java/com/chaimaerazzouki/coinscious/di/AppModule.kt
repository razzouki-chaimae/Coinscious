package com.chaimaerazzouki.coinscious.di

import com.chaimaerazzouki.dashboard.DashboardViewModel
import com.chaimaerazzouki.database.di.databaseModule
import com.chaimaerazzouki.di.dataModule
import com.chaimaerazzouki.domain.usecase.AddTransactionUseCase
import com.chaimaerazzouki.domain.usecase.DeleteTransactionUseCase
import com.chaimaerazzouki.domain.usecase.GetTransactionUseCase
import com.chaimaerazzouki.quicklog.QuickLogViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// Koin modules aggregation
val appModule = module {

    single {
        GetTransactionUseCase(get())
    }

    single {
        AddTransactionUseCase(get())
    }

    single {
        DeleteTransactionUseCase(get())
    }

    viewModel {
        DashboardViewModel(
            getTransactionsUseCase = get()
        )
    }

    viewModel {
        QuickLogViewModel(
            addTransactionUseCase = get()
        )
    }
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