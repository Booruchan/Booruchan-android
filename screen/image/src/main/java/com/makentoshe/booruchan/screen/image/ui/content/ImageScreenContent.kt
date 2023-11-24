package com.makentoshe.booruchan.screen.image.ui.content

import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState

@Composable
internal fun ImageScreenContent(
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) = when (val contentState = screenState.contentState) {
    ContentState.Loading -> {
        ImageScreenContentLoading()
    }

    is ContentState.Failure -> {
        ImageScreenContentFailure(contentState = contentState, screenEvent = screenEvent)
    }

    is ContentState.Content -> {
        ImageScreenContentSuccess(contentState = contentState, screenState = screenState, screenEvent = screenEvent)
    }
}
