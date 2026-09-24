package com.chaimaerazzouki.quicklog

data class QuickLogUiState(
    val amount: String = "",
    val selectedCategory: String? = null,
    val note: String = "",
    val isSaving: Boolean = false,
    val saveCompleted: Boolean = false,
    val errorMessage: String? = null
) {
    val canSave: Boolean
        get() =
            amount.toDoubleOrNull()?.let { it > 0 } == true &&
                    selectedCategory != null &&
                    !isSaving
}