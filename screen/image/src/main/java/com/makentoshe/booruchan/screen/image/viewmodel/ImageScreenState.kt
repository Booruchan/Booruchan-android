package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.compose.runtime.Immutable
import com.makentoshe.booruchan.screen.image.entity.SamplePostUiState

@Immutable
data class ImageScreenState(
    val sourceId: String,
    val postId: String,
    val contentState: ContentState,
) {
    companion object {
        val InitialState = ImageScreenState(
            sourceId = "",
            postId = "",
            contentState = ContentState.Loading,
        )
    }
}

@Immutable
sealed interface ContentState {
    object Loading: ContentState

    @Immutable
    data class Failure(
        val title: String,
        val description: String,
        val button: String,
        val event: ImageScreenEvent,
    ): ContentState

    @Immutable
    data class Content(
        val samplePostUiState: SamplePostUiState,
    ): ContentState
}