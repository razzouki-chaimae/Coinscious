package com.chaimaerazzouki.quicklog.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.SurfaceWhite
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary

@Composable
fun NoteInput(
    note: String,
    onNoteChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = note,
        onValueChange = onNoteChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 6.dp,
                bottom = 12.dp
            ),
        placeholder = {
            Text(
                text = "e.g. Lunch with friends",
                color = TextSecondary
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        textStyle = CoinsciousTypography.bodyMedium.copy(
            color = TextPrimary
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceWhite,
            unfocusedContainerColor = SurfaceWhite,
            disabledContainerColor = SurfaceWhite,
            errorContainerColor = SurfaceWhite,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            errorIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
        )
    )
}