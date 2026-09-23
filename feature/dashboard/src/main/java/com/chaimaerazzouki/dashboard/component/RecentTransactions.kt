package com.chaimaerazzouki.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.ExpenseRed
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary

private data class RecentTransactionUi(
    val title: String,
    val date: String,
    val amount: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun RecentTransactions(
    modifier: Modifier = Modifier
) {
    val transactions = listOf(
        RecentTransactionUi(
            title = "Coffee Shop",
            date = "Today, 8:47 AM",
            amount = "-$4.50",
            icon = Icons.Default.Coffee
        ),
        RecentTransactionUi(
            title = "Metro Ride",
            date = "Today, 7:32 AM",
            amount = "-$2.40",
            icon = Icons.Default.DirectionsSubway
        ),
        RecentTransactionUi(
            title = "Grocery Store",
            date = "Yesterday",
            amount = "-$23.16",
            icon = Icons.Default.ShoppingCart
        )
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transactions",
                color = TextPrimary
            )

            Text(
                text = "See all",
                color = com.chaimaerazzouki.designsystem.ForestGreen
            )
        }

        transactions.forEach { transaction ->
            TransactionRow(transaction)
        }
    }
}

@Composable
private fun TransactionRow(
    transaction: RecentTransactionUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = transaction.icon,
            contentDescription = null,
            tint = TextPrimary
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp)
        ) {
            Text(
                text = transaction.title,
                color = TextPrimary
            )

            Text(
                text = transaction.date,
                color = TextSecondary
            )
        }

        Text(
            text = transaction.amount,
            color = ExpenseRed
        )
    }
}