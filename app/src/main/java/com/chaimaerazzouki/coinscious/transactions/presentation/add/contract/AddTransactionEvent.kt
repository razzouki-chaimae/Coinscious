package com.chaimaerazzouki.coinscious.transactions.presentation.add.contract

import com.chaimaerazzouki.coinscious.core.base.UiEvent
import com.chaimaerazzouki.coinscious.transactions.domain.enums.Category
import com.chaimaerazzouki.coinscious.transactions.domain.enums.TransactionType

sealed class AddTransactionEvent : UiEvent {
    data class AmountChanged(val amount: String) : AddTransactionEvent()
    data class TypeChanged(val type: TransactionType) : AddTransactionEvent()
    data class CategorySelected(val category: Category) : AddTransactionEvent()
    data class NoteChanged(val note: String) : AddTransactionEvent()
    object SaveClicked : AddTransactionEvent()
    object DismissSuccess : AddTransactionEvent()
}