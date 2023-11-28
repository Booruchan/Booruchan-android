package com.makentoshe.booruchan.screen.source.ui.topappbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.SmallTopAppBar
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenTopbar(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(modifier = Modifier) {

    SmallTopAppBar(
        title = {
            SourceScreenTopbarTitle(screenState = screenState)
        },
        navigationIcon = {
            SourceScreenTopbarNavigationIcon(screenEvent = screenEvent)
        },
        actions = {
            SourceScreenTopbarActionIcon(screenEvent = screenEvent)
        },
    )

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)
}
