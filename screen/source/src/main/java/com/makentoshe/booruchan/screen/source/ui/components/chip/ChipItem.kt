package com.makentoshe.booruchan.screen.source.ui.components.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.entity.TagUiState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.library.uikit.foundation.CloseIcon
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun ChipItem(
    state: TagUiState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = FilterChip(
    selected = false,
    onClick = { },
    label = { SecondaryText(text = state.string, color = BooruchanTheme.colors.foreground) },
    trailingIcon = {
        Box(modifier = Modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = { screenEvent(SourceScreenEvent.SearchTagRemove(tag = state.tag)) }
        )) {
            CloseIcon(modifier = Modifier.size(24.dp))
        }
    }
)
