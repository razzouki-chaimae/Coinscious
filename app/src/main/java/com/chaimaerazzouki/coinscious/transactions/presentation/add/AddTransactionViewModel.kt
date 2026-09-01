package com.chaimaerazzouki.coinscious.transactions.presentation.add

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.coinscious.R
import com.chaimaerazzouki.coinscious.core.base.BaseViewModel
import com.chaimaerazzouki.coinscious.transactions.presentation.add.contract.AddTransactionEffect
import com.chaimaerazzouki.coinscious.transactions.presentation.add.contract.AddTransactionEvent
import com.chaimaerazzouki.coinscious.transactions.presentation.add.contract.AddTransactionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val application: Application
) : BaseViewModel<AddTransactionState, AddTransactionEvent, AddTransactionEffect>(
    initialState = AddTransactionState()
) {

    override fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.AmountChanged -> validateAmount(event.amount)
            is AddTransactionEvent.TypeChanged -> setState {
                copy(selectedType = event.type)
            }
            is AddTransactionEvent.CategorySelected -> setState {
                copy(selectedCategory = event.category, categoryError = null)
            }
            is AddTransactionEvent.NoteChanged -> setState {
                copy(note = event.note)
            }
            is AddTransactionEvent.SaveClicked -> saveTransaction()
            is AddTransactionEvent.DismissSuccess -> {
                setState { copy(isSuccess = false) }
                sendEffect(AddTransactionEffect.NavigateBack)
            }
        }
    }

    private fun validateAmount(amount: String) {
        val error = when {
            amount.isBlank() -> null
            amount.toDoubleOrNull() == null ->
                application.getString(R.string.error_invalid_amount)
            amount.toDouble() <= 0 ->
                application.getString(R.string.error_amount_positive)
            else -> null
        }

        setState {
            copy(
                amount = amount,
                amountError = error
            )
        }
    }

    private fun saveTransaction() {
        val currentState = state.value

        // Validate
        if (!currentState.isValid) {
            val context = application.applicationContext
            setState {
                copy(
                    amountError = if (parsedAmount == null || parsedAmount!!.value <= 0)
                        context.getString(R.string.error_amount_required) else null,
                    categoryError = if (selectedCategory == null)
                        context.getString(R.string.error_category_required) else null
                )
            }
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true) }

            try {
                // TODO: Save to database
                delay(800) // Simulate save

                setState { copy(isLoading = false, isSuccess = true) }
                sendEffect(AddTransactionEffect.ShowSuccessAnimation)

                delay(500)
                sendEffect(AddTransactionEffect.NavigateBack)

            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                sendEffect(AddTransactionEffect.ShowError(
                    application.getString(R.string.error_save_failed)
                ))
            }
        }
    }
}