package com.chaimaerazzouki.coinscious.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chaimaerazzouki.coinscious.core.navigation.CoinsciousDestination
import com.chaimaerazzouki.designsystem.CoinsciousTheme
import com.chaimaerazzouki.ui.component.CoinsciousBottomBar
import com.chaimaerazzouki.ui.component.CoinsciousBottomBarItem

@Composable
fun CoinsciousApp() {
    CoinsciousTheme {

        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val showBottomBar = currentRoute != CoinsciousDestination.QuickLog.route

        val selectedItem = when (currentRoute) {
            CoinsciousDestination.Dashboard.route ->
                CoinsciousBottomBarItem.HOME

            CoinsciousDestination.Budgets.route ->
                CoinsciousBottomBarItem.BUDGETS

            CoinsciousDestination.Settings.route ->
                CoinsciousBottomBarItem.SETTINGS

            else ->
                CoinsciousBottomBarItem.HOME
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            bottomBar = {
                if (showBottomBar) {
                    CoinsciousBottomBar(
                        selectedItem = selectedItem,
                        onItemSelected = { item ->
                            when (item) {
                                CoinsciousBottomBarItem.HOME -> {
                                    navController.navigate(
                                        CoinsciousDestination.Dashboard.route
                                    ) {
                                        popUpTo(
                                            CoinsciousDestination.Dashboard.route
                                        ) {
                                            inclusive = false
                                        }
                                        launchSingleTop = true
                                    }
                                }

                                CoinsciousBottomBarItem.BUDGETS -> {
                                    navController.navigate(
                                        CoinsciousDestination.Budgets.route
                                    ) {
                                        launchSingleTop = true
                                    }
                                }

                                CoinsciousBottomBarItem.TRANSACTIONS -> {
                                    // Transactions screen will be added later.
                                }

                                CoinsciousBottomBarItem.SETTINGS -> {
                                    navController.navigate(
                                        CoinsciousDestination.Settings.route
                                    ) {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        },
                        onAddClick = {
                            navController.navigate(
                                CoinsciousDestination.QuickLog.route
                            )
                        }
                    )
                }
            }
        ) { innerPadding ->

            CoinsciousNavHost(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}