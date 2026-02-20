package com.chaimaerazzouki.coinscious.core.navigation

sealed class Destination(val route: String) {
    object Transactions : Destination("transactions")
    object AddTransaction : Destination("transactions/add")
    object Budget : Destination("budget")
    object Goals : Destination("goals")
    object Analytics : Destination("analytics")
    object Settings : Destination("settings")
}