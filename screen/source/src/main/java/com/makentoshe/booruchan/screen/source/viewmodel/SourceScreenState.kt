package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.paging.PagingData
import com.makentoshe.library.uikit.entity.TagUiState
import com.makentoshe.booruchan.screen.source.entity.AutocompleteUiState
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.library.uikit.component.rating.RatingComponentState
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonState
import com.makentoshe.library.uikit.component.tags.TagsComponentState
import com.makentoshe.library.uikit.component.tags.TagsContentState
import kotlinx.coroutines.flow.Flow
import javax.annotation.concurrent.Immutable

data class SourceScreenState(
    val sourceId: String,
    val sourceTitle: String,
    val contentState: ContentState,
    val searchState: SourceScreenSearchState,
    val snackbarState: SnackbackState,

    val ratingComponentState: RatingComponentState,
    val tagsComponentState: TagsComponentState,
) {
    companion object {
        val InitialState = SourceScreenState(
            sourceId = "",
            sourceTitle = "",
            contentState = ContentState.Loading,
            snackbarState = SnackbackState.None,
            searchState = SourceScreenSearchState(
                value = "",
                label = "Ex: blue_sky cloud 1girl",
                autocompleteState = AutocompleteState.None,
                fullScreenState = SourceScreenSearchState.FullScreenState.Collapsed,
            ),
            ratingComponentState = RatingComponentState(
                visible = false,
                ratingTagSegmentedButtonState = RatingSegmentedButtonState(values = emptyList())
            ),
            tagsComponentState = TagsComponentState(
                generalTagsContentState = TagsContentState(
                    visible = false,
                    tags = emptySet(),
                ),
                characterTagsContentState = TagsContentState(
                    visible = false,
                    tags = emptySet(),
                ),
                artistTagsContentState = TagsContentState(
                    visible = false,
                    tags = emptySet(),
                ),
                copyrightTagsContentState = TagsContentState(
                    visible = false,
                    tags = emptySet(),
                ),
                metadataTagsContentState = TagsContentState(
                    visible = false,
                    tags = emptySet(),
                ),
            )
        )
    }
}

@Immutable
sealed interface ContentState {

    @Immutable
    object Loading : ContentState

    @Immutable
    data class Success(
        val pagerFlow: Flow<PagingData<PreviewPostUiState>>,
    ) : ContentState

    @Immutable
    data class Failure(
        val title: String,
        val description: String,
    ) : ContentState
}

@Immutable
data class SourceScreenSearchState(
    /** TextField value */
    val value: String,
    /** Search example label */
    val label: String,
    /** Autocompletion state */
    val autocompleteState: AutocompleteState,
    /** full screen search view state */
    val fullScreenState: FullScreenState,
) {
    sealed interface FullScreenState {
        object Expanded : FullScreenState
        object Collapsed : FullScreenState
    }
}

@Immutable
data class SourceScreenRatingTagContentState(
    /** Is component should be visible */
    val visible: Boolean,
    /** All values for "rating" metadata tag */
    val ratingTagSegmentedButtonState: RatingSegmentedButtonState,
)

@Immutable
sealed interface AutocompleteState {

    @Immutable
    object None : AutocompleteState

    @Immutable
    object Loading : AutocompleteState

    @Immutable
    data class Content(
        val autocomplete: Set<AutocompleteUiState>,
    ) : AutocompleteState
}

@Immutable
sealed interface SnackbackState {
    object None : SnackbackState

    data class Content(val message: String) : SnackbackState
}