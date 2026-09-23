package com.chaimaerazzouki.coinscious.ui

import androidx.compose.material3.Scaffold
import com.chaimaerazzouki.designsystem.CoinsciousTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chaimaerazzouki.ui.component.CoinsciousBottomBar
import com.chaimaerazzouki.ui.component.CoinsciousBottomBarItem

@Composable
fun CoinsciousApp() {
    CoinsciousTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                CoinsciousBottomBar(
                    selectedItem = CoinsciousBottomBarItem.HOME,
                    onItemSelected = { item ->
                        // Navigation will be wired here
                        // when the other screens are implemented.
                    },
                    onAddClick = {
                        // Quick Log navigation will be wired here.
                    }
                )
            }
        ) { innerPadding ->
            CoinsciousNavHost(
                innerPadding = innerPadding
            )
        }
    }
}