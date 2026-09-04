package com.chaimaerazzouki.coinscious.core.navigation

sealed interface CoinsciousDestination {

    val route: String

    data object Dashboard : CoinsciousDestination {
        override val route = "dashboard"
    }

    data object QuickLog : CoinsciousDestination {
        override val route = "quick_log"
    }

    data object Budgets : CoinsciousDestination {
        override val route = "budgets"
    }

    data object Settings : CoinsciousDestination {
        override val route = "settings"
    }
}