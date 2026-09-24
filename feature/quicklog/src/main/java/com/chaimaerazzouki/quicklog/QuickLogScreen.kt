package com.chaimaerazzouki.quicklog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.WarmBackground
import com.chaimaerazzouki.quicklog.component.AmountInput
import com.chaimaerazzouki.quicklog.component.CategoryGrid
import com.chaimaerazzouki.quicklog.component.NoteInput
import com.chaimaerazzouki.quicklog.component.PaymentMethodSelector
import com.chaimaerazzouki.quicklog.component.QuickLogHeader
import com.chaimaerazzouki.quicklog.model.quickLogCategories
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuickLogScreen(
    onClose: () -> Unit,
    onSaved: () -> Unit,
    viewModel: QuickLogViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    /*
     * Navigation remains outside the ViewModel.
     *
     * The ViewModel only reports that saving completed.
     */
    LaunchedEffect(uiState.saveCompleted) {
        if (uiState.saveCompleted) {
            onSaved()
        }
    }

    QuickLogContent(
        uiState = uiState,
        onClose = onClose,
        onSave = viewModel::onSaveClicked,
        onAmountChanged = viewModel::onAmountChanged,
        onCategorySelected = viewModel::onCategorySelected,
        onNoteChanged = viewModel::onNoteChanged
    )
}

@Composable
private fun QuickLogContent(
    uiState: QuickLogUiState,
    onClose: () -> Unit,
    onSave: () -> Unit,
    onAmountChanged: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onNoteChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WarmBackground)
            .padding(horizontal = 16.dp)
    ) {
        QuickLogHeader(
            uiState = uiState,
            onCloseClick = onClose,
            onSaveClick = onSave
        )

        Text(
            text = "Log it now, worry less later.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            style = CoinsciousTypography.bodyMedium,
            color = TextSecondary
        )

        AmountInput(
            amount = uiState.amount,
            onAmountChanged = onAmountChanged
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        PaymentMethodSelector(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Choose Category",
            modifier = Modifier.padding(top = 24.dp),
            style = CoinsciousTypography.titleMedium,
            color = TextPrimary
        )

        CategoryGrid(
            categories = quickLogCategories,
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = onCategorySelected,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "Note / Purpose (optional)",
            modifier = Modifier.padding(top = 12.dp),
            style = CoinsciousTypography.labelLarge,
            color = TextPrimary
        )

        NoteInput(
            note = uiState.note,
            onNoteChanged = onNoteChanged
        )

        if (uiState.isSaving) {
            SavingIndicator()
        }

        uiState.errorMessage?.let { message ->
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = CoinsciousTypography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun SavingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .width(24.dp)
            .height(24.dp),
        color = PrimaryGreen,
        strokeWidth = 2.dp
    )
}