package com.makentoshe.library.uikit.foundation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = FlowRow(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
    verticalArrangement = Arrangement.Top,
    content = content,
)

@Composable
fun ChipItem(
    label: @Composable () -> Unit,
    onClick: () -> Unit = {},
) = FilterChip(
    selected = false,
    onClick = onClick,
    label = label,
)

@Composable
fun CloseChipItem(
    label: @Composable () -> Unit,
    onClick: () -> Unit = {},
    onClose: () -> Unit = {},
) = FilterChip(
    selected = false,
    onClick = onClick,
    label = label,
    trailingIcon = {
        Box(
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClose,
            )
        ) {
            CloseIcon(modifier = Modifier.size(24.dp))
        }
    }
)
