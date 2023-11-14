package com.makentoshe.booruchan.screen.source.ui.topappbar

import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.TitleText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenTopbarTitle(screenState: SourceScreenState) = TitleText(
    text = screenState.sourceTitle,
    color = BooruchanTheme.colors.accent,
)
