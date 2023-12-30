package com.makentoshe.booruchan.screen.source.ui.components.chip

import androidx.compose.runtime.Composable
import com.makentoshe.library.uikit.entity.TagUiState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.library.uikit.foundation.CloseChipItem
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun ChipItem(
    state: TagUiState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = CloseChipItem(
    label = { SecondaryText(text = state.string, color = BooruchanTheme.colors.foreground) },
    onClose = { screenEvent(SourceScreenEvent.SearchTagRemove(tag = state.tag)) }
)