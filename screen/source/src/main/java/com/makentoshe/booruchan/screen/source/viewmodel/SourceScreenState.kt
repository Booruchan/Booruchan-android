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
import javax.annotation.concurrent.Immutable

data class SourceScreenState(
    val sourceId: String,
    val sourceTitle: String,
    val backdropValue: BackdropValue,
    val contentState: ContentState,
    val searchState: SearchState,
) {
    companion object {
        val InitialState = SourceScreenState(
            sourceId = "",
            sourceTitle = "",
            backdropValue = BackdropValue.Revealed,
            contentState = ContentState.Loading,
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
    object Loading : ContentState

    data class Success(
        val pagerFlow: Flow<PagingData<PreviewPostUiState>>,
    ) : ContentState

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
    object None: AutocompleteState

    object Loading: AutocompleteState

    data class Content(
        val autocomplete: Set<AutocompleteUiState>,
    ): AutocompleteState
}