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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.dashboard.component.DashboardHeader
import com.chaimaerazzouki.dashboard.component.QuickActions
import com.chaimaerazzouki.dashboard.component.RecentTransactions
import com.chaimaerazzouki.dashboard.component.SafeToSpendCard

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onManualEntryClick: () -> Unit = {},
    onScanReceiptClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 8.dp)
    ) {
        DashboardHeader()

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SafeToSpendCard(
                amount = "$28.45",
                progress = 0.72f
            )

            QuickActions(
                onManualEntryClick = onManualEntryClick,
                onScanReceiptClick = onScanReceiptClick
            )

            RecentTransactions()

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}