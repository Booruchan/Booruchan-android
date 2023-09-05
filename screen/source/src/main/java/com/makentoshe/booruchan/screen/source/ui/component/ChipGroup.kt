@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.makentoshe.booruchan.screen.source.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ChipGroup(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = FlowRow(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
    verticalAlignment = Alignment.Top,
    content = content,
)