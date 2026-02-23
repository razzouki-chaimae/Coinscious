package com.chaimaerazzouki.coinscious.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.dashboard.domain.enums.DashboardEffect
import com.chaimaerazzouki.coinscious.dashboard.domain.enums.DashboardEvent
import com.chaimaerazzouki.coinscious.dashboard.presentation.DashboardViewModel
import com.chaimaerazzouki.coinscious.dashboard.ui.components.BalanceCard
import com.chaimaerazzouki.coinscious.dashboard.ui.components.QuickActions
import com.chaimaerazzouki.coinscious.dashboard.ui.components.RecentTransactions
import com.chaimaerazzouki.coinscious.dashboard.ui.components.WaveChart
import com.chaimaerazzouki.coinscious.ui.theme.CoinsciousTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    onNavigateToTransactionDetail: (String) -> Unit,
    onNavigateToAllTransactions: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior() // Collapses when you scroll

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DashboardEffect.NavigateToAddExpense -> onNavigateToAddExpense()
                is DashboardEffect.NavigateToAddIncome -> onNavigateToAddIncome()
                is DashboardEffect.NavigateToTransactionDetail -> onNavigateToTransactionDetail(effect.transactionId)
                is DashboardEffect.NavigateToAllTransactions -> onNavigateToAllTransactions()
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Coinscious",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        // quick access to add expense
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(DashboardEvent.AddExpenseClick) },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = androidx.compose.foundation.shape.CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Oops! ${state.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            state.dashboardData != null -> {
                val data = state.dashboardData!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(padding)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Hero balance card
                    BalanceCard(data = data)

                    // Quick actions
                    QuickActions(
                        todayExpense = Money(80.50),
                        monthIncome = Money(1200.00),
                        onAddExpense = { viewModel.onEvent(DashboardEvent.AddExpenseClick) },
                        onAddIncome = { viewModel.onEvent(DashboardEvent.AddIncomeClick) }
                    )

                    // Wave chart
                    WaveChart(
                        data = data.weeklySpending,
                        average = data.dailyAverage
                    )

                    // Recent transactions
                    RecentTransactions(
                        transactions = data.recentTransactions,
                        onTransactionClick = { id ->
                            viewModel.onEvent(DashboardEvent.TransactionClick(id))
                        },
                        onViewAllClick = {
                            viewModel.onEvent(DashboardEvent.ViewAllTransactionsClick)
                        }
                    )

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenDarkPreview() {
    CoinsciousTheme {
        DashboardScreen(
            onNavigateToAddExpense = {},
            onNavigateToAddIncome = {},
            onNavigateToTransactionDetail = {},
            onNavigateToAllTransactions = {},
            onNavigateToSettings = {}
        )
    }
}