package com.chaimaerazzouki.coinscious.transactions.presentation.add.contract

import com.chaimaerazzouki.coinscious.core.base.UiState
import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category
import com.chaimaerazzouki.coinscious.transactions.domain.enums.TransactionType

data class AddTransactionState(
    val amount: String = "",
    val amountError: String? = null,
    val selectedType: TransactionType = TransactionType.EXPENSE,
    val selectedCategory: Category? = null,
    val categoryError: String? = null,
    val note: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
) : UiState {

    val parsedAmount: Money?
        get() = amount.toDoubleOrNull()?.let { Money(it) }

    val isValid: Boolean
        get() = parsedAmount != null &&
                (parsedAmount?.value ?: 0.0) > 0 &&
                selectedCategory != null &&
                amountError == null
}