package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ForestGreen
import com.chaimaerazzouki.designsystem.LightSage
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary

@Composable
fun QuickActions(
    onManualEntryClick: () -> Unit,
    onScanReceiptClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        QuickActionCard(
            modifier = Modifier.weight(1f),
            background = LightSage,
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            iconBackground = ForestGreen,
            title = "Quick\nManual Entry",
            subtitle = "Log in seconds",
            onClick = onManualEntryClick
        )

        QuickActionCard(
            modifier = Modifier.weight(1f),
            background = Color(0xFFF0EDF4),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.DocumentScanner,
                    contentDescription = null,
                    tint = TextPrimary
                )
            },
            iconBackground = Color(0xFFE0DCE7),
            title = "Scan Receipt\n(ML)",
            subtitle = "Let AI handle it",
            onClick = onScanReceiptClick
        )
    }
}

@Composable
private fun QuickActionCard(
    modifier: Modifier,
    background: Color,
    iconBackground: Color,
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    iconBackground,
                    RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Text(
                text = title,
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.size(2.dp))

            Text(
                text = subtitle,
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
    }
}