package com.makentoshe.booruchan.screen.source.ui.content

import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.source.viewmodel.ContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState

@Composable
internal fun SourceScreenContent(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = when (val contentState = screenState.contentState) {
    // Displays full screen loading indicator till we able to draw content
    is ContentState.Loading -> {
        SourceScreenContentLoading()
    }
    // We cannot draw content, so display an error
    is ContentState.Failure -> {
        SourceScreenContentFailure(title = contentState.title, description = contentState.description)
    }
    // Draw content
    is ContentState.Success -> {
        SourceScreenContentSuccess(screenState = screenState, contentState = contentState, screenEvent = screenEvent)
    }
}


