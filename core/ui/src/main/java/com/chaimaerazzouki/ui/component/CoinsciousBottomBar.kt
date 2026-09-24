package com.chaimaerazzouki.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ForestGreen
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.VeryLightGreen
import com.chaimaerazzouki.designsystem.WarmBackground

enum class CoinsciousBottomBarItem {
    HOME,
    BUDGETS,
    TRANSACTIONS,
    SETTINGS
}

@Composable
fun CoinsciousBottomBar(
    selectedItem: CoinsciousBottomBarItem,
    onItemSelected: (CoinsciousBottomBarItem) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                shape = RectangleShape,
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
            .background(WarmBackground)
            .navigationBarsPadding()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == CoinsciousBottomBarItem.HOME,
                icon = Icons.Default.Home,
                label = "Home",
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.HOME)
                }
            )

            BottomItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == CoinsciousBottomBarItem.BUDGETS,
                icon = Icons.Default.Wallet,
                label = "Budgets",
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.BUDGETS)
                }
            )

            Spacer(
                modifier = Modifier.size(68.dp)
            )

            BottomItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == CoinsciousBottomBarItem.TRANSACTIONS,
                icon = Icons.AutoMirrored.Filled.List,
                label = "Transactions",
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.TRANSACTIONS)
                }
            )

            BottomItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == CoinsciousBottomBarItem.SETTINGS,
                icon = Icons.Default.Settings,
                label = "Settings",
                onClick = {
                    onItemSelected(CoinsciousBottomBarItem.SETTINGS)
                }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(52.dp)
                .background(
                    color = ForestGreen,
                    shape = CircleShape
                )
                .clickable(onClick = onAddClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add transaction",
                tint = Color.White,
                modifier = Modifier.size(27.dp)
            )
        }
    }
}

@Composable
private fun BottomItem(
    modifier: Modifier,
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(34.dp)
                .background(
                    color = if (selected) {
                        VeryLightGreen
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(19.dp),
                tint = if (selected) {
                    ForestGreen
                } else {
                    TextSecondary
                }
            )
        }

        Text(
            text = label,
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
            color = if (selected) {
                TextPrimary
            } else {
                TextSecondary
            }
        )
    }
}