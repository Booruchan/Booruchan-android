package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.compose.runtime.Immutable
import com.makentoshe.booruchan.screen.image.entity.SamplePostImageState
import com.makentoshe.booruchan.screen.image.entity.SamplePostScoreState
import com.makentoshe.booruchan.screen.image.entity.SamplePostTagsState
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonState

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
        val samplePostImageState: SamplePostImageState,
        val samplePostTagsState: SamplePostTagsState,
        val samplePostScoreState: SamplePostScoreState,
        val samplePostRatingState: RatingSegmentedButtonState,
    ): ContentState
}