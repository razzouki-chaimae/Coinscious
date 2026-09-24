package com.chaimaerazzouki.quicklog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.SurfaceWhite
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary

@Composable
fun PaymentMethodSelector(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = SurfaceWhite
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountBalanceWallet,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Cash",
                style = CoinsciousTypography.labelLarge,
                color = TextPrimary
            )

            Spacer(
                modifier = Modifier.width(4.dp)
            )

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}