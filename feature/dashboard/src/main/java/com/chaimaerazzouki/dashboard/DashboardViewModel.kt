package com.chaimaerazzouki.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.domain.usecase.GetTransactionUseCase
import com.chaimaerazzouki.model.Transaction
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    getTransactionsUseCase: GetTransactionUseCase
) : ViewModel() {

    val transactions: StateFlow<List<Transaction>> =
        getTransactionsUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}