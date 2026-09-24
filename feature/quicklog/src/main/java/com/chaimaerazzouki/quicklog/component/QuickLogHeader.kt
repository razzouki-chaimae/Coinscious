package com.chaimaerazzouki.quicklog.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.quicklog.QuickLogUiState

@Composable
fun QuickLogHeader(
    uiState: QuickLogUiState,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCloseClick
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                tint = TextPrimary
            )
        }

        Text(
            text = "Quick Log ⚡",
            modifier = Modifier.weight(1f),
            style = CoinsciousTypography.titleLarge,
            color = TextPrimary
        )

        IconButton(
            onClick = onSaveClick,
            enabled = uiState.canSave
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Save",
                tint = if (uiState.canSave) {
                    PrimaryGreen
                } else {
                    TextSecondary
                }
            )
        }
    }
}