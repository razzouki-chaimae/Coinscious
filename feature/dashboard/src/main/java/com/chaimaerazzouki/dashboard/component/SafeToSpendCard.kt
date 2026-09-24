package com.chaimaerazzouki.dashboard.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ForestGreen
import com.chaimaerazzouki.designsystem.ProgressTrack
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.SurfaceWhite

@Composable
fun SafeToSpendCard(
    amount: String,
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
                        style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = amount,
                    style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Keep it up! You're on track.",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(10.dp))

                SpendingProgress(
                    progress = progress
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

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
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .height(9.dp)
                .clip(RoundedCornerShape(50))
                .background(ForestGreen)
        )
    }
}

@Composable
private fun PlantIllustration() {
    androidx.compose.foundation.Canvas(
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
                size.width * .25f,
                size.height * .58f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * .5f,
                size.height * .3f
            ),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(5.dp.toPx())
        )

        drawRect(
            color = potDark,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * .23f,
                size.height * .52f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * .54f,
                size.height * .12f
            )
        )

        // Stem
        drawLine(
            color = leafDark,
            start = androidx.compose.ui.geometry.Offset(
                size.width * .5f,
                size.height * .55f
            ),
            end = androidx.compose.ui.geometry.Offset(
                size.width * .5f,
                size.height * .2f
            ),
            strokeWidth = 3.dp.toPx()
        )

        drawOval(
            color = leaf,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * .18f,
                size.height * .24f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * .38f,
                size.height * .25f
            )
        )

        drawOval(
            color = leafDark,
            topLeft = androidx.compose.ui.geometry.Offset(
                size.width * .48f,
                size.height * .1f
            ),
            size = androidx.compose.ui.geometry.Size(
                size.width * .35f,
                size.height * .3f
            )
        )
    }
}