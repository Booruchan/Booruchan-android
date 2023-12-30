package com.makentoshe.library.uikit.component.rating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RatingComponent(
    state: RatingComponentState,
    onClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) = if (state.visible) Column(modifier = modifier) {
    Spacer(modifier = Modifier.size(16.dp))

    RatingSegmentedButtonRow(
        state = state.ratingTagSegmentedButtonState,
        enabled = true,
        onClick = onClick,
    )
} else Unit