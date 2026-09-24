package com.chaimaerazzouki.coinscious.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chaimaerazzouki.designsystem.CoinsciousTheme
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
                    onItemSelected = {
                        // Navigation will be connected when
                        // the corresponding screens are implemented.
                    },
                    onAddClick = {
                        // Quick Log navigation later.
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