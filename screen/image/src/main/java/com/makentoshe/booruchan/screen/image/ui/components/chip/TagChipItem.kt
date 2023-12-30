package com.makentoshe.booruchan.screen.image.ui.components.chip

import androidx.compose.runtime.Composable
import com.makentoshe.library.uikit.entity.TagUiState
import com.makentoshe.library.uikit.foundation.ChipItem
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun TagChipItem(
    state: TagUiState,
) = ChipItem(
    label = { SecondaryText(text = state.string, color = BooruchanTheme.colors.foreground) },
)
