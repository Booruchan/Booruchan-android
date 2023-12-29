package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.paging.PagingData
import com.makentoshe.booruchan.screen.entity.TagUiState
import com.makentoshe.booruchan.screen.source.entity.AutocompleteUiState
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonState
import kotlinx.coroutines.flow.Flow
import javax.annotation.concurrent.Immutable

data class SourceScreenState(
    val sourceId: String,
    val sourceTitle: String,
    val contentState: ContentState,
    val searchState: SourceScreenSearchState,
    val snackbarState: SnackbackState,

    /** State for rating metadata tag */
    val ratingTagContentState: SourceScreenRatingTagContentState,
    /** State for all general tags */
    val generalTagsContentState: SourceScreenGeneralTagsContentState,
    /** State for all character tags */
    val characterTagsContentState: SourceScreenCharacterTagsContentState,
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
            ratingTagContentState = SourceScreenRatingTagContentState(
                visible = false,
                ratingTagSegmentedButtonState = TagsRatingSegmentedButtonState(values = emptyList())
            ),
            generalTagsContentState = SourceScreenGeneralTagsContentState(
                visible = false,
                tags = emptySet(),
            ),
            characterTagsContentState = SourceScreenCharacterTagsContentState(
                visible = false,
                tags = emptySet(),
            ),
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
    val ratingTagSegmentedButtonState: TagsRatingSegmentedButtonState,
)

@Immutable
data class SourceScreenGeneralTagsContentState(
    /** Is component should be visible */
    val visible: Boolean,
    /** All general tags that was added to the filter */
    val tags: Set<TagUiState>,
)

@Immutable
data class SourceScreenCharacterTagsContentState(
    /** Is component should be visible */
    val visible: Boolean,
    /** All character tags that was added to the filter */
    val tags: Set<TagUiState>,
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