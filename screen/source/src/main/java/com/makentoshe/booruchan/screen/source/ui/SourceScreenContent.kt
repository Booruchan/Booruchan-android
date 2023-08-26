package com.makentoshe.booruchan.screen.source.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.source.viewmodel.ContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState


@Composable
internal fun SourceScreenContent(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    when (val contentState = screenState.contentState) {
        ContentState.Loading -> {
            Text("Loading")
        }
        is ContentState.Failure -> {
            Text("Failure: ${contentState.string}")
        }
        is ContentState.Success -> SourceScreenUiSuccess(
            screenState = screenState,
            contentState = contentState,
        )
    }
}

@Composable
private fun SourceScreenUiSuccess(
    screenState: SourceScreenState,
    contentState: ContentState.Success,
) {
    SourceLazyVerticalStaggeredGrid(
        screenState = screenState,
        contentState = contentState,
    )
}

