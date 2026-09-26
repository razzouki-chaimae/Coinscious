package com.chaimaerazzouki.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
            // TODO: Implement loading state
        }

        is DashboardUiState.Success -> {
            DashboardContent(
                state = state,
                onManualEntryClick = onManualEntryClick,
                onScanReceiptClick = onScanReceiptClick
            )
        }

        is DashboardUiState.Error -> {
            // TODO: Implement error state
        }
    }
}

@Composable
private fun DashboardContent(
    state: DashboardUiState.Success,
    onManualEntryClick: () -> Unit,
    onScanReceiptClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DashboardHeader()

        /*
         * The header intentionally extends behind the Safe To Spend card.
         * The content below it is pulled upward while keeping horizontal
         * margins so the cards don't touch the screen edges.
         */
        Column(
            modifier = Modifier
                .overlapUp(56.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
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

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

/**
 * Moves the content visually upward without changing the size of the
 * scrolling layout.
 *
 * This recreates the intentional overlap between the landscape header
 * and the Safe To Spend card.
 */
private fun Modifier.overlapUp(amount: Dp): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val offsetPx = amount.roundToPx()

        layout(
            width = placeable.width,
            height = placeable.height - offsetPx
        ) {
            placeable.place(
                x = 0,
                y = -offsetPx
            )
        }
    }