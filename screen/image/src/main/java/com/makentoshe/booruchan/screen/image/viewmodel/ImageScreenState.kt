package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.compose.runtime.Immutable
import com.makentoshe.booruchan.screen.entity.TagUiState
import com.makentoshe.booruchan.screen.image.entity.SamplePostUiState
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonState

@Immutable
data class ImageScreenState(
    val sourceId: String,
    val postId: String,
    val sourceTitle: String,
    val contentState: ContentState,
) {
    companion object {
        val InitialState = ImageScreenState(
            sourceId = "",
            postId = "",
            sourceTitle = "",
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
        val button: String?,
        val event: ImageScreenEvent?,
    ): ContentState

    @Immutable
    data class Content(
        val samplePostUiState: SamplePostUiState,
        val ratingSegmentedButtonState: TagsRatingSegmentedButtonState,
    ): ContentState
}