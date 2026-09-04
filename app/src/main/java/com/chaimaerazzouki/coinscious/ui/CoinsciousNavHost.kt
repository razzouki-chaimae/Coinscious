package com.chaimaerazzouki.coinscious.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chaimaerazzouki.coinscious.core.navigation.CoinsciousDestination

@Composable
fun CoinsciousNavHost(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CoinsciousDestination.Dashboard.route
    ) {
        composable(CoinsciousDestination.Dashboard.route) {
            // Dashboard
        }

        composable(CoinsciousDestination.QuickLog.route) {
            // Quick Log
        }

        composable(CoinsciousDestination.Budgets.route) {
            // Budgets
        }

        composable(CoinsciousDestination.Settings.route) {
            // Settings
        }
    }
}