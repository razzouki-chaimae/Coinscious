package com.chaimaerazzouki.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chaimaerazzouki.dashboard.component.DashboardHeader
import com.chaimaerazzouki.dashboard.component.QuickActions
import com.chaimaerazzouki.dashboard.component.RecentTransactions
import com.chaimaerazzouki.dashboard.component.SafeToSpendCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    onManualEntryClick: () -> Unit,
    onScanReceiptClick: () -> Unit,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {

        DashboardUiState.Loading -> {
            //TODO: TO BE IMPLEMENTED
            //DashboardLoading()
        }

        is DashboardUiState.Success -> {
            DashboardContent(
                state = state,
                onManualEntryClick = onManualEntryClick,
                onScanReceiptClick = onScanReceiptClick
            )
        }

        is DashboardUiState.Error -> {
            //TODO: TO BE IMPLEMENTED
            //DashboardError(
            //    message = state.message
            //)
        }
    }
}

@Composable
private fun DashboardContent(
    state: DashboardUiState.Success,
    onManualEntryClick: () -> Unit,
    onScanReceiptClick: () -> Unit
) {
    Column {
        DashboardHeader()

        SafeToSpendCard(
            amount = state.safeToSpendToday,
            progress = state.safeToSpendProgress
        )

        QuickActions(
            onManualEntryClick = onManualEntryClick,
            onScanReceiptClick = onScanReceiptClick
        )

        RecentTransactions(
            transactions = state.recentTransactions
        )
    }
}