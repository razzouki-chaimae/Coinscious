package com.chaimaerazzouki.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.WarmBackground

enum class CoinsciousBottomBarItem {
    HOME,
    BUDGETS,
    TRANSACTIONS,
    SETTINGS
}

@Composable
fun CoinsciousBottomBar(
    selectedItem: CoinsciousBottomBarItem,
    onItemSelected: (CoinsciousBottomBarItem) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        NavigationBar(
            containerColor = WarmBackground
        ) {
            NavigationBarItem(
                selected = selectedItem == CoinsciousBottomBarItem.HOME,
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.HOME)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home"
                    )
                },
                label = {
                    Text("Home")
                }
            )

            NavigationBarItem(
                selected = selectedItem == CoinsciousBottomBarItem.BUDGETS,
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.BUDGETS)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Wallet,
                        contentDescription = "Budgets"
                    )
                },
                label = {
                    Text("Budgets")
                }
            )

            NavigationBarItem(
                selected = false,
                onClick = onAddClick,
                icon = {
                    FloatingActionButton(
                        onClick = onAddClick,
                        containerColor = PrimaryGreen,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add transaction"
                        )
                    }
                },
                label = {}
            )

            NavigationBarItem(
                selected = selectedItem == CoinsciousBottomBarItem.TRANSACTIONS,
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.TRANSACTIONS)
                },
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "Transactions"
                    )
                },
                label = {
                    Text("Transactions")
                }
            )

            NavigationBarItem(
                selected = selectedItem == CoinsciousBottomBarItem.SETTINGS,
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.SETTINGS)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
                label = {
                    Text("Settings")
                }
            )
        }
    }
}