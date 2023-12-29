package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.paging.PagingData
import com.makentoshe.booruchan.screen.source.entity.AutocompleteUiState
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.entity.TagType
import com.makentoshe.booruchan.screen.source.entity.TagUiState
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
                tags = emptySet(),
                autocompleteState = AutocompleteState.None,
                fullScreenState = SourceScreenSearchState.FullScreenState.Collapsed,
            ),
            ratingTagContentState = SourceScreenRatingTagContentState(
                visible = false,
                ratingTagSegmentedButtonState = TagsRatingSegmentedButtonState(values = emptyList())
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
    /** All tags that was add to the filter except rating */
    val tags: Set<TagUiState>,

    /** Autocompletion state */
    val autocompleteState: AutocompleteState,
    /** full screen search view state */
    val fullScreenState: FullScreenState,
) {
    val generalTags: List<TagUiState>
        get() = tags.filter { it.type == TagType.General }

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