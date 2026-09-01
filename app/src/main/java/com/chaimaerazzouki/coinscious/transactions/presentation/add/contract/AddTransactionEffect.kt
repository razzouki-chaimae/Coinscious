package com.chaimaerazzouki.coinscious.transactions.presentation.add.contract

import com.chaimaerazzouki.coinscious.core.base.UiEffect

sealed class AddTransactionEffect : UiEffect {
    object NavigateBack : AddTransactionEffect()
    data class ShowError(val message: String) : AddTransactionEffect()
    object ShowSuccessAnimation : AddTransactionEffect()
}