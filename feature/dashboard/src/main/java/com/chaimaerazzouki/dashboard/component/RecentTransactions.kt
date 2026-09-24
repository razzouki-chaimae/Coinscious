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
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.DividerColor
import com.chaimaerazzouki.designsystem.ExpenseRed
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.VeryLightGreen
import com.chaimaerazzouki.model.Transaction

//TODO: TO BE REMOVED
/*private data class RecentTransactionUi(
    val merchant: String,
    val time: String,
    val amount: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)*/

@Composable
fun RecentTransactions(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    /*val transactions = listOf(
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
    )*/

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

        transactions
            .take(4)
            .forEachIndexed { index, transaction ->

                RecentTransactionRow(
                    transaction = transaction
                )

                if (index < minOf(transactions.size, 4) - 1) {
                    HorizontalDivider(
                        color = DividerColor,
                        thickness = 1.dp
                    )
                }
            }
    }
}

@Composable
private fun RecentTransactionRow(
    transaction: Transaction
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
            // TODO: TO BE REVIEWED
            Icon(
                imageVector = Icons.Default.LocalCafe, //transaction.icon,
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
                //TODO : TO BE REVIEWED
                text = transaction.timestamp.toString(),
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }

        // TODO: TO BE MOVED
        val formattedAmount =
            if (transaction.amount < 0) {
                "-$${kotlin.math.abs(transaction.amount)}"
            } else {
                "+$${transaction.amount}"
            }

        Text(
            text = formattedAmount,
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            color = ExpenseRed
        )
    }
}