package com.chaimaerazzouki.coinscious.dashboard.presentation

import com.chaimaerazzouki.coinscious.dashboard.domain.model.DashboardData
import com.chaimaerazzouki.coinscious.core.base.UiState

// MVI: State, Events, Effects
data class DashboardState(
    val isLoading: Boolean = true,
    val dashboardData: DashboardData? = null,
    val error: String? = null
) : UiState