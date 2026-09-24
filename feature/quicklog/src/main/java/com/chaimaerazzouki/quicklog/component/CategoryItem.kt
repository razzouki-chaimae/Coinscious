package com.chaimaerazzouki.quicklog.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.SurfaceGreen
import com.chaimaerazzouki.designsystem.SurfaceWhite
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.ui.component.CategoryIcon
import com.chaimaerazzouki.quicklog.model.QuickLogCategory

@Composable
fun CategoryItem(
    category: QuickLogCategory,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryIcon = CategoryIcon.forCategory(category.id)

    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(20.dp),
        color = if (selected) {
            SurfaceGreen
        } else {
            SurfaceWhite
        },
        border = if (selected) {
            BorderStroke(
                width = 1.5.dp,
                color = PrimaryGreen
            )
        } else {
            null
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(SurfaceGreen),
                contentAlignment = Alignment.Center
            ) {
                if (categoryIcon != null) {
                    Image(
                        painter = painterResource(categoryIcon),
                        contentDescription = category.name,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.MoreHoriz,
                        contentDescription = category.name,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = category.name,
                style = CoinsciousTypography.labelLarge,
                color = TextPrimary
            )
        }
    }
}