package com.chaimaerazzouki.coinscious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chaimaerazzouki.coinscious.core.navigation.Destination
import com.chaimaerazzouki.coinscious.dashboard.ui.DashboardScreen
import com.chaimaerazzouki.coinscious.ui.theme.CoinsciousTheme

// Single Activity entry point
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinsciousTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Destination.Dashboard.route
                    ) {
                        composable(Destination.Dashboard.route) {
                            DashboardScreen(
                                onNavigateToAddExpense = {
                                    // TODO: Navigate to add expense
                                },
                                onNavigateToAddIncome = {
                                    // TODO: Navigate to add income
                                },
                                onNavigateToTransactionDetail = { id ->
                                    // TODO: Navigate to detail
                                },
                                onNavigateToAllTransactions = {
                                    // TODO: Navigate to list
                                },
                                onNavigateToSettings = {
                                    // TODO: Navigate to settings
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
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