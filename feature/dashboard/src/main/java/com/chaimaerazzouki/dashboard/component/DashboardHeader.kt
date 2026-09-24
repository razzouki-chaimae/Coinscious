package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.dashboard.R
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.WarmBackground

val DashboardHeaderHeight: Dp = 280.dp

@Composable
fun DashboardHeader(modifier: Modifier = Modifier) {
    val statusBarHeight = WindowInsets.statusBars
        .asPaddingValues()
        .calculateTopPadding()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(DashboardHeaderHeight + statusBarHeight)
            .background(WarmBackground)
    ) {
        // 1. Freepik landscape, slightly desaturated
        Image(
            painter = painterResource(R.drawable.header_landscape),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            // tweak these two to move the sun/hills around
            alignment = BiasAlignment(horizontalBias = -0.4f, verticalBias = 0.3f),
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply { setToSaturation(0.65f) }
            )
        )

        // 2. Warm wash: lightens the dark mountains, keeps text readable,
        //    and fades into the screen background at the bottom
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to WarmBackground.copy(alpha = 0.55f),
                        0.5f to WarmBackground.copy(alpha = 0.35f),
                        1.0f to WarmBackground.copy(alpha = 0.75f)
                    )
                )
        )

        // 3. Content
        Column(modifier = Modifier.statusBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Menu, "Menu", tint = TextPrimary)
                }

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PlantLeafIcon()
                    Spacer(Modifier.size(6.dp))
                    Text(
                        text = "Coinscious",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )
                }

                IconButton(onClick = {}) {
                    Icon(Icons.Default.NotificationsNone, "Notifications", tint = TextPrimary)
                }
            }

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp)) {
                Text(
                    text = "Good morning, Chaimae! ☀️",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                Spacer(Modifier.height(4.dp))
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
                size.width * .35f,
                0f
            ),
            size = Size(
                size.width * .45f,
                size.height * .65f
            )
        )

        drawOval(
            color = Color(0xFF6EA96A),
            topLeft = Offset(
                0f,
                size.height * .3f
            ),
            size = Size(
                size.width * .5f,
                size.height * .45f
            )
        )

        drawLine(
            color = Color(0xFF396E3E),
            start = Offset(
                size.width * .45f,
                size.height
            ),
            end = Offset(
                size.width * .48f,
                size.height * .25f
            ),
            strokeWidth = 2.dp.toPx()
        )
    }
}

private fun DrawScope.drawHill(
    color: Color,
    startX: Float,
    peakY: Float,
    width: Float,
    height: Float
) {
    val path = Path().apply {
        moveTo(
            startX,
            size.height
        )

        cubicTo(
            startX + width * .2f,
            peakY + height * .2f,
            startX + width * .35f,
            peakY - height * .15f,
            startX + width * .5f,
            peakY
        )

        cubicTo(
            startX + width * .7f,
            peakY + height * .15f,
            startX + width * .8f,
            peakY + height * .1f,
            startX + width,
            size.height
        )

        close()
    }

    drawPath(
        path = path,
        color = color
    )
}