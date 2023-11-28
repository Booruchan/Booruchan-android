package com.makentoshe.booruchan.screen.image.ui.topappbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState
import com.makentoshe.library.uikit.foundation.SmallTopAppBar
import com.makentoshe.library.uikit.foundation.TitleText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun ImageScreenTopAppBar(
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) = Column(modifier = Modifier) {

    SmallTopAppBar(
        navigationIcon = {
            ImageScreenTopAppBarNavigationIcon(screenEvent = screenEvent)
        },
        title = {
            ImageScreenTopAppBarTitle(screenState = screenState)
        },
    )

    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)
}
