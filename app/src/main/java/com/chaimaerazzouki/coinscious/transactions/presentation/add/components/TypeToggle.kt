package com.chaimaerazzouki.coinscious.transactions.presentation.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.coinscious.R
import com.chaimaerazzouki.coinscious.transactions.domain.enums.TransactionType
import com.chaimaerazzouki.coinscious.ui.theme.AlertCoral
import com.chaimaerazzouki.coinscious.ui.theme.GrowthGreen

@Composable
fun TypeToggle(
    selectedType: TransactionType,
    onTypeSelected: (TransactionType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TransactionType.entries.forEach { type ->
            val isSelected = type == selectedType
            val color = when (type) {
                TransactionType.EXPENSE -> AlertCoral
                TransactionType.INCOME -> GrowthGreen
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable { onTypeSelected(type) }
                    .background(
                        if (isSelected) color.copy(alpha = 0.15f)
                        else MaterialTheme.colorScheme.surface
                    )
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (type) {
                        TransactionType.EXPENSE -> stringResource(R.string.action_expense)
                        TransactionType.INCOME -> stringResource(R.string.action_income)
                    },
                    color = if (isSelected) color else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}