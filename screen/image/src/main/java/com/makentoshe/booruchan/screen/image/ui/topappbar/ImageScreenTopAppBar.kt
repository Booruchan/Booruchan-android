package com.makentoshe.booruchan.screen.image.ui.topappbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState
import com.makentoshe.library.uikit.foundation.ArrowBackIcon
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
            ImageScreenTopbarTitle(screenState = screenState)
        },
//    actions = {
//        SourceScreenTopbarActionIcon(screenEvent = screenEvent)
//    },
    )

    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)
}

@Composable
internal fun ImageScreenTopAppBarNavigationIcon(
    screenEvent: (ImageScreenEvent) -> Unit,
) = Box(
    modifier = Modifier.size(48.dp).clickable {
//        screenEvent(SourceScreenEvent.NavigationBack)
    },
    contentAlignment = Alignment.Center,
    content = { ArrowBackIcon() }
)

@Composable
internal fun ImageScreenTopbarTitle(screenState: ImageScreenState) = TitleText(
    text = screenState.sourceTitle,
    color = BooruchanTheme.colors.accent,
)
