package com.chaimaerazzouki.coinscious.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chaimaerazzouki.coinscious.core.navigation.CoinsciousDestination
import com.chaimaerazzouki.dashboard.DashboardScreen
import com.chaimaerazzouki.quicklog.QuickLogScreen

@Composable
fun CoinsciousNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = CoinsciousDestination.Dashboard.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(CoinsciousDestination.Dashboard.route) {
            DashboardScreen(
                onManualEntryClick = {
                    navController.navigate(
                        CoinsciousDestination.QuickLog.route
                    )
                },
                onScanReceiptClick = {
                    //TODO: TO BE SPECIFIED
                }
            )
        }

        composable(CoinsciousDestination.QuickLog.route) {
            QuickLogScreen(
                onClose = {
                    navController.popBackStack()
                },
                onSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable(CoinsciousDestination.Budgets.route) {
            // Budgets screen
        }

        composable(CoinsciousDestination.Settings.route) {
            // Settings screen
        }
    }
}