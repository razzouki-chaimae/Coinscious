package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Tram
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ExpenseRed
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.VeryLightGreen

private data class RecentTransactionUi(
    val merchant: String,
    val time: String,
    val amount: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun RecentTransactions(
    modifier: Modifier = Modifier
) {
    val transactions = listOf(
        RecentTransactionUi(
            merchant = "Coffee Shop",
            time = "Today, 8:47 AM",
            amount = "-$4.50",
            icon = Icons.Default.LocalCafe
        ),
        RecentTransactionUi(
            merchant = "Metro Ride",
            time = "Today, 7:32 AM",
            amount = "-$2.40",
            icon = Icons.Default.Tram
        ),
        RecentTransactionUi(
            merchant = "Grocery Store",
            time = "Yesterday",
            amount = "-$23.16",
            icon = Icons.Default.ShoppingCart
        ),
        RecentTransactionUi(
            merchant = "Electricity Bill",
            time = "May 18",
            amount = "-$68.90",
            icon = Icons.Default.Bolt
        )
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transactions",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "See all",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                color = com.chaimaerazzouki.designsystem.ForestGreen
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        transactions.forEachIndexed { index, transaction ->

            RecentTransactionRow(
                transaction = transaction
            )

            if (index < transactions.lastIndex) {
                HorizontalDivider(
                    color = com.chaimaerazzouki.designsystem.DividerColor,
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
private fun RecentTransactionRow(
    transaction: RecentTransactionUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    color = VeryLightGreen,
                    shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = null,
                modifier = Modifier.size(19.dp),
                tint = com.chaimaerazzouki.designsystem.ForestGreen
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transaction.merchant,
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                color = TextPrimary
            )

            Text(
                text = transaction.time,
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }

        Text(
            text = transaction.amount,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            color = ExpenseRed
        )
    }
}