package com.chaimaerazzouki.coinscious.dashboard.ui.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.coinscious.R
import com.chaimaerazzouki.coinscious.core.utils.Money
import com.chaimaerazzouki.coinscious.core.utils.Strings
import com.chaimaerazzouki.coinscious.dashboard.domain.model.DailySpending
import com.chaimaerazzouki.coinscious.ui.theme.RichGold
import com.chaimaerazzouki.coinscious.ui.theme.SoftGold

@Composable
fun WaveChart(
    data: List<DailySpending>?,
    average: Money?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.chart_title),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.chart_average, average?.format() ?: Money.ZERO.format()),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Handle null or empty data early
            if (data.isNullOrEmpty() || data.size < 2) {
                // Show empty state
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No data yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            } else {
                // Safe to use non-nullable data
                WaveChartContent(
                    data = data, // Now non-nullable
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
    }
}

@Composable
private fun WaveChartContent(
    data: List<DailySpending>, // Non-nullable!
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            val maxAmount = data.maxOf { it.amount.value }.coerceAtLeast(1.0)
            val width = size.width
            val height = size.height

            // Calculate points - data is guaranteed non-null here
            val points = data.mapIndexed { index, daily ->
                val x = if (data.size > 1) {
                    (index.toFloat() / (data.size - 1)) * width
                } else {
                    width / 2
                }

                val amountRatio = (daily.amount.value / maxAmount).toFloat().coerceIn(0f, 1f)
                val y = height - (amountRatio * height * 0.8f) - 10f

                Offset(x, y.coerceIn(10f, height - 10f))
            }

            // Create filled wave path - NO NULL CHECKS NEEDED!
            val wavePath = Path().apply {
                // Start at bottom left
                moveTo(0f, height)
                lineTo(points.first().x, points.first().y)

                // Smooth curves through points
                for (i in 0 until points.size - 1) {
                    val current = points[i]
                    val next = points[i + 1]

                    val controlX1 = current.x + (next.x - current.x) * 0.5f
                    val controlY1 = current.y
                    val controlX2 = current.x + (next.x - current.x) * 0.5f
                    val controlY2 = next.y

                    cubicTo(
                        controlX1, controlY1,
                        controlX2, controlY2,
                        next.x, next.y
                    )
                }

                // Close to bottom right
                lineTo(points.last().x, height)
                lineTo(0f, height)
                close()
            }

            // Draw filled area
            drawPath(
                path = wavePath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        RichGold.copy(alpha = 0.5f),
                        SoftGold.copy(alpha = 0.1f)
                    ),
                    startY = 0f,
                    endY = height
                ),
                style = Fill
            )

            // Draw line
            val linePath = Path().apply {
                moveTo(points.first().x, points.first().y)

                for (i in 0 until points.size - 1) {
                    val current = points[i]
                    val next = points[i + 1]

                    val controlX1 = current.x + (next.x - current.x) * 0.5f
                    val controlY1 = current.y
                    val controlX2 = current.x + (next.x - current.x) * 0.5f
                    val controlY2 = next.y

                    cubicTo(
                        controlX1, controlY1,
                        controlX2, controlY2,
                        next.x, next.y
                    )
                }
            }

            drawPath(
                path = linePath,
                color = RichGold,
                style = Stroke(width = 3f, cap = StrokeCap.Round)
            )

            // Draw points
            points.forEachIndexed { index, point ->
                val isToday = data[index].isToday

                if (isToday) {
                    drawCircle(
                        color = RichGold.copy(alpha = 0.4f),
                        radius = 14f,
                        center = point
                    )
                }

                drawCircle(
                    color = if (isToday) RichGold else Color.White,
                    radius = if (isToday) 6f else 4f,
                    center = point
                )

                if (!isToday) {
                    drawCircle(
                        color = RichGold,
                        radius = 4f,
                        center = point,
                        style = Stroke(width = 2f)
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Day labels
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
    ) {
        data.forEach { daily ->
            Text(
                text = daily.dayOfWeek,
                style = MaterialTheme.typography.bodySmall,
                color = if (daily.isToday) {
                    RichGold
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                },
                fontWeight = if (daily.isToday) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}