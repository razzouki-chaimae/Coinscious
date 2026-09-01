package com.chaimaerazzouki.coinscious.transactions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chaimaerazzouki.coinscious.R
import com.chaimaerazzouki.coinscious.transactions.presentation.add.AddTransactionViewModel
import com.chaimaerazzouki.coinscious.transactions.presentation.add.components.AmountInput
import com.chaimaerazzouki.coinscious.transactions.presentation.add.components.CategorySelector
import com.chaimaerazzouki.coinscious.transactions.presentation.add.components.NoteInput
import com.chaimaerazzouki.coinscious.transactions.presentation.add.components.TypeToggle
import com.chaimaerazzouki.coinscious.transactions.presentation.add.contract.AddTransactionEffect
import com.chaimaerazzouki.coinscious.transactions.presentation.add.contract.AddTransactionEvent
import com.chaimaerazzouki.coinscious.ui.theme.RichGold
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddTransactionViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AddTransactionEffect.NavigateBack -> onNavigateBack()
                is AddTransactionEffect.ShowError -> {
                    // Show snackBar or toast
                }
                is AddTransactionEffect.ShowSuccessAnimation -> {
                    // Show success animation
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_add_transaction),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Expense / Income Toggle
            TypeToggle(
                selectedType = state.selectedType,
                onTypeSelected = {
                    viewModel.onEvent(AddTransactionEvent.TypeChanged(it))
                }
            )

            // Amount Input
            AmountInput(
                amount = state.amount,
                error = state.amountError,
                onAmountChange = {
                    viewModel.onEvent(AddTransactionEvent.AmountChanged(it))
                }
            )

            // Category Selector
            CategorySelector(
                selectedCategory = state.selectedCategory,
                error = state.categoryError,
                onCategorySelected = {
                    viewModel.onEvent(AddTransactionEvent.CategorySelected(it))
                }
            )

            // Note Input
            NoteInput(
                note = state.note,
                onNoteChange = {
                    viewModel.onEvent(AddTransactionEvent.NoteChanged(it))
                }
            )

            //Spacer(modifier = Modifier.weight(1f))

            // Save Button
            Button(
                onClick = { viewModel.onEvent(AddTransactionEvent.SaveClicked) },
                enabled = state.isValid && !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RichGold,
                    disabledContainerColor = RichGold.copy(alpha = 0.3f)
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.button_save_transaction),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}