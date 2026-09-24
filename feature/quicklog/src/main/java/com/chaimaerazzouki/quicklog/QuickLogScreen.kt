package com.chaimaerazzouki.quicklog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import com.chaimaerazzouki.designsystem.CoinsciousTypography
import com.chaimaerazzouki.designsystem.PrimaryGreen
import com.chaimaerazzouki.designsystem.SurfaceGreen
import com.chaimaerazzouki.designsystem.SurfaceWhite
import com.chaimaerazzouki.designsystem.TextPrimary
import com.chaimaerazzouki.designsystem.TextSecondary
import com.chaimaerazzouki.designsystem.WarmBackground
import com.chaimaerazzouki.ui.component.CategoryIcon

@Composable
fun QuickLogScreen(
    onClose: () -> Unit,
    onSaved: () -> Unit,
    viewModel: QuickLogViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmBackground)
            .padding(horizontal = 16.dp)
    ) {

        // Top actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClose
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close"
                )
            }

            Text(
                text = "Quick Log ⚡",
                modifier = Modifier.weight(1f),
                style = CoinsciousTypography.titleLarge,
                color = TextPrimary
            )

            IconButton(
                onClick = {
                    viewModel.save(onSaved)
                },
                enabled = state.canSave && !state.isSaving
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save",
                    tint = if (state.canSave) {
                        PrimaryGreen
                    } else {
                        TextSecondary
                    }
                )
            }
        }

        Text(
            text = "Log it now, worry less later.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            style = CoinsciousTypography.bodyMedium,
            color = TextSecondary
        )

        // Amount
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (state.amount.isBlank()) {
                    "$0.00"
                } else {
                    "$${state.amount}"
                },
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }

        TextField(
            value = state.amount,
            onValueChange = viewModel::onAmountChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            colors = androidx.compose.material3.TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // Payment method
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = SurfaceWhite
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalanceWallet,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Cash",
                        style = CoinsciousTypography.labelLarge,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Text(
            text = "Choose Category",
            modifier = Modifier.padding(top = 24.dp),
            style = CoinsciousTypography.titleMedium,
            color = TextPrimary
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = quickLogCategories,
                key = { it.id }
            ) { category ->

                CategoryItem(
                    category = category,
                    selected = state.selectedCategory == category.id,
                    onClick = {
                        viewModel.onCategorySelected(category.id)
                    }
                )
            }
        }

        Text(
            text = "Note / Purpose (optional)",
            modifier = Modifier.padding(top = 12.dp),
            style = CoinsciousTypography.labelLarge,
            color = TextPrimary
        )

        TextField(
            value = state.note,
            onValueChange = viewModel::onNoteChanged,
            modifier = Modifier
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
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
private fun CategoryItem(
    category: QuickLogCategory,
    selected: Boolean,
    onClick: () -> Unit
) {
    val categoryIcon = CategoryIcon.forCategory(category.id)

    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(20.dp),
        color = if (selected) {
            SurfaceGreen
        } else {
            SurfaceWhite
        },
        border = if (selected) {
            BorderStroke(1.5.dp, PrimaryGreen)
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
                        painter = painterResource(id = categoryIcon),
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = category.name,
                style = CoinsciousTypography.labelLarge,
                color = TextPrimary
            )
        }
    }
}