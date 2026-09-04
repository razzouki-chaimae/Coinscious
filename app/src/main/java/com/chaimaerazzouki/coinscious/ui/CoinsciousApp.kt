package com.chaimaerazzouki.coinscious.ui

import androidx.compose.material3.Scaffold
import com.chaimaerazzouki.designsystem.CoinsciousTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CoinsciousApp() {
    CoinsciousTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                // CoinsciousTopBar(...)
            },
            bottomBar = {
                // CoinsciousBottomBar(...)
            }
        ) { innerPadding ->

            CoinsciousNavHost(
                innerPadding = innerPadding
            )
        }
    }
}