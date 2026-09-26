package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.dashboard.R
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.WarmBackground

@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(WarmBackground)
    ) {
        Image(
            painter = painterResource(R.drawable.header_landscape),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alignment = BiasAlignment(
                horizontalBias = -0.4f,
                verticalBias = 0.3f
            ),
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply {
                    setToSaturation(0.65f)
                }
            )
        )

        /*
         * Warm overlay:
         * - keeps the illustration visible
         * - makes text readable
         * - blends the bottom of the image into the dashboard background
         */
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to WarmBackground.copy(alpha = 0.55f),
                        0.5f to WarmBackground.copy(alpha = 0.35f),
                        1.0f to WarmBackground.copy(alpha = 0.78f)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = TextPrimary
                    )
                }

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PlantLeafIcon()

                    Spacer(
                        modifier = Modifier.size(6.dp)
                    )

                    Text(
                        text = "Coinscious",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "Notifications",
                        tint = TextPrimary
                    )
                }
            }

            Column(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 4.dp
                )
            ) {
                Text(
                    text = "Good morning, Chaimae! ☀️",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "You're making great progress.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun PlantLeafIcon() {
    Canvas(
        modifier = Modifier.size(26.dp)
    ) {
        val leafColor = Color(0xFF4E8B52)

        drawOval(
            color = leafColor,
            topLeft = Offset(
                size.width * 0.35f,
                0f
            ),
            size = Size(
                size.width * 0.45f,
                size.height * 0.65f
            )
        )

        drawOval(
            color = Color(0xFF6EA96A),
            topLeft = Offset(
                0f,
                size.height * 0.3f
            ),
            size = Size(
                size.width * 0.5f,
                size.height * 0.45f
            )
        )

        drawLine(
            color = Color(0xFF396E3E),
            start = Offset(
                size.width * 0.45f,
                size.height
            ),
            end = Offset(
                size.width * 0.48f,
                size.height * 0.25f
            ),
            strokeWidth = 2.dp.toPx()
        )
    }
}