package com.chaimaerazzouki.coinscious.dashboard.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaimaerazzouki.coinscious.R
import com.chaimaerazzouki.coinscious.core.utils.Strings
import com.chaimaerazzouki.coinscious.dashboard.domain.model.DashboardData
import com.chaimaerazzouki.coinscious.ui.theme.AlertCoral
import com.chaimaerazzouki.coinscious.ui.theme.CautionAmber
import com.chaimaerazzouki.coinscious.ui.theme.RichGold
import com.chaimaerazzouki.coinscious.ui.theme.SuccessGreen

// This card shows the remaining balance and a progress bar towards the monthly goal.
// It also gives encouraging messages based on how well the user is doing.
@Composable
fun BalanceCard(
    data: DashboardData,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Animate the progress bar smoothly when the percentage changes
    val progress by animateFloatAsState(
        targetValue = data.progressPercentage,
        label = "progress"
    )

    // Determine the color and message based on how close the user is to their budget limit
    val statusColor = when {
        data.isOverBudget -> AlertCoral
        data.isNearingLimit -> CautionAmber
        else -> SuccessGreen
    }

    // Fun, encouraging messages to keep the user motivated depending on their progress
    val encouragement = Strings.getEncouragement(
        context = context,
        isHealthy = data.progressPercentage < 0.5f,
        isCaution = data.isNearingLimit,
        isCritical = data.isOverBudget
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encouraging label
            Text(
                text = stringResource(R.string.dashboard_remaining_label),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Big remaining amount
            Text(
                text = data.remaining.format(),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                ),
                color = RichGold
            )

            Text(
                text = stringResource(R.string.dashboard_remaining_suffix),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Progress bar with gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(RichGold, statusColor)
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = Strings.getProgressLabel(context, (progress * 100).toInt()),
                    style = MaterialTheme.typography.bodyMedium,
                    color = statusColor,
                    fontWeight = FontWeight.SemiBold
                )
                // Countdown to reset
                Text(
                    text = Strings.getDaysLeft(context, data.daysUntilReset),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Encouragement message
            Text(
                text = encouragement,
                style = MaterialTheme.typography.bodyLarge,
                color = statusColor,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}