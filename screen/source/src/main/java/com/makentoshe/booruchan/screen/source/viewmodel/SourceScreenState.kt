@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.paging.PagingData
import com.makentoshe.booruchan.screen.source.entity.AutocompleteUiState
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.entity.TagType
import com.makentoshe.booruchan.screen.source.entity.TagUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.annotation.concurrent.Immutable

data class SourceScreenState(
    val sourceId: String,
    val sourceTitle: String,
    val backdropValue: BackdropValue,
    val contentState: ContentState,
    val searchState: SearchState,
    val snackbarState: SnackbackState,
) {
    companion object {
        val InitialState = SourceScreenState(
            sourceId = "",
            sourceTitle = "",
            backdropValue = BackdropValue.Revealed,
            contentState = ContentState.Loading,
            snackbarState = SnackbackState.None,
            searchState = SearchState(
                value = "",
                label = "Ex: blue_sky cloud 1girl",
                tags = emptySet(),
                autocompleteState = AutocompleteState.None,
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
data class SearchState(
    /** TextField value */
    val value: String,
    /** Search example label */
    val label: String,
    /** All tags that was add to the filter */
    val tags: Set<TagUiState>,
    /** Autocompletion state */
    val autocompleteState: AutocompleteState,
) {
    val generalTags: List<TagUiState>
        get() = tags.filter { it.type == TagType.General }
}

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
    object None: SnackbackState

    data class Content(val message: String): SnackbackState
}