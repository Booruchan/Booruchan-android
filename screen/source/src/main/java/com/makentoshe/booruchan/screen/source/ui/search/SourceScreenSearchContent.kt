package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenSearchContent(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize().background(BooruchanTheme.colors.dashboard).clickable(enabled = false) {},
) {
    SourceScreenSearchTopAppBar(screenState = screenState, screenEvent = screenEvent)

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)

    Box(modifier = Modifier.fillMaxSize()) {
        SourceScreenSearchFilters(screenState = screenState, screenEvent = screenEvent)
        SourceScreenSearchSuggestions(screenState = screenState, screenEvent = screenEvent)
    }
}

