package com.chaimaerazzouki.quicklog.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaimaerazzouki.designsystem.TextPrimary

@Composable
fun AmountInput(
    amount: String,
    onAmountChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = amount,
            onValueChange = onAmountChanged,
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            ),
            placeholder = {
                Text(
                    text = "$0.00",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary.copy(alpha = 0.4f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )
    }
}