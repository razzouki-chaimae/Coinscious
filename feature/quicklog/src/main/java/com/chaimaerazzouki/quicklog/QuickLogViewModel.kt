package com.chaimaerazzouki.quicklog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.domain.usecase.AddTransactionUseCase
import com.chaimaerazzouki.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID

class QuickLogViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuickLogUiState())
    val uiState: StateFlow<QuickLogUiState> = _uiState.asStateFlow()

    fun onAmountChanged(value: String) {
        if (
            value.isEmpty() ||
            value.matches(Regex("^\\d*(\\.\\d{0,2})?$"))
        ) {
            _uiState.value = _uiState.value.copy(
                amount = value
            )
        }
    }

    fun onCategorySelected(categoryId: String) {
        _uiState.value = _uiState.value.copy(
            selectedCategory = categoryId
        )
    }

    fun onNoteChanged(value: String) {
        if (value.length <= 60) {
            _uiState.value = _uiState.value.copy(
                note = value
            )
        }
    }

    fun save(onSaved: () -> Unit) {
        val state = _uiState.value
        val amount = state.amount.toDoubleOrNull() ?: return
        val category = state.selectedCategory ?: return

        viewModelScope.launch {

            _uiState.value = state.copy(
                isSaving = true
            )

            /*
             * Expenses are stored as negative values.
             * Income is stored as a positive value.
             */
            val signedAmount =
                if (category == CATEGORY_INCOME) {
                    amount
                } else {
                    -amount
                }

            addTransactionUseCase(
                Transaction(
                    id = UUID.randomUUID().toString(),
                    amount = signedAmount,
                    merchant = state.note.ifBlank {
                        category.replaceFirstChar { it.uppercase() }
                    },
                    categoryId = category,
                    timestamp = Instant.now(),
                    isAutoCaptured = false
                )
            )

            _uiState.value = QuickLogUiState()

            onSaved()
        }
    }

    companion object {
        const val CATEGORY_FOOD = "food"
        const val CATEGORY_TRANSPORT = "transport"
        const val CATEGORY_BILLS = "bills"
        const val CATEGORY_SHOPPING = "shopping"
        const val CATEGORY_HEALTH = "health"
        const val CATEGORY_ENTERTAINMENT = "entertainment"
        const val CATEGORY_EDUCATION = "education"
        const val CATEGORY_INCOME = "income"
        const val CATEGORY_OTHER = "other"
    }
}