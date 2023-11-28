package com.makentoshe.booruchan.screen.image.ui.topappbar

import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState
import com.makentoshe.library.uikit.foundation.TitleText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun ImageScreenTopAppBarTitle(screenState: ImageScreenState) = TitleText(
    text = screenState.sourceTitle,
    color = BooruchanTheme.colors.accent,
)
