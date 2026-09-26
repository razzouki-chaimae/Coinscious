package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ForestGreen
import com.chaimaerazzouki.designsystem.ProgressTrack
import com.chaimaerazzouki.designsystem.SurfaceWhite
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary

@Composable
fun SafeToSpendCard(
    amount: Double,
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceWhite)
            .padding(
                start = 20.dp,
                top = 18.dp,
                end = 14.dp,
                bottom = 18.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Safe To Spend Today",
                        style = MaterialTheme.typography.labelLarge,
                        color = TextPrimary
                    )

                    Spacer(
                        modifier = Modifier.size(5.dp)
                    )

                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Information",
                        modifier = Modifier.size(15.dp),
                        tint = TextSecondary
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = amount.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    color = ForestGreen
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "Keep it up! You're on track.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                SpendingProgress(
                    progress = progress
                )
            }

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            PlantIllustration()
        }
    }
}

@Composable
private fun SpendingProgress(
    progress: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(9.dp)
            .clip(RoundedCornerShape(50))
            .background(ProgressTrack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    progress.coerceIn(0f, 1f)
                )
                .height(9.dp)
                .clip(RoundedCornerShape(50))
                .background(ForestGreen)
        )
    }
}

@Composable
private fun PlantIllustration() {
    Canvas(
        modifier = Modifier.size(70.dp)
    ) {
        val potColor = Color(0xFFC97842)
        val potDark = Color(0xFFA9582F)
        val leaf = Color(0xFF72A94F)
        val leafDark = Color(0xFF4F8A3C)

        // Pot
        drawRoundRect(
            color = potColor,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * 0.25f,
                size.height * 0.58f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * 0.5f,
                size.height * 0.3f
            ),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                5.dp.toPx()
            )
        )

        // Pot rim
        drawRect(
            color = potDark,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * 0.23f,
                size.height * 0.52f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * 0.54f,
                size.height * 0.12f
            )
        )

        // Stem
        drawLine(
            color = leafDark,
            start = androidx.compose.ui.geometry.Offset(
                size.width * 0.5f,
                size.height * 0.55f
            ),
            end = androidx.compose.ui.geometry.Offset(
                size.width * 0.5f,
                size.height * 0.2f
            ),
            strokeWidth = 3.dp.toPx()
        )

        // Left leaf
        drawOval(
            color = leaf,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * 0.18f,
                size.height * 0.24f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * 0.38f,
                size.height * 0.25f
            )
        )

        // Right leaf
        drawOval(
            color = leafDark,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * 0.48f,
                size.height * 0.1f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * 0.35f,
                size.height * 0.3f
            )
        )
    }
}