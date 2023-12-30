package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.ui.components.chip.ChipItem
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.component.tags.TagsComponent

@Composable
internal fun SourceScreenSearchContentTags(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
) {
    // Show rating tag selector only if there are any tags to select
    if (screenState.ratingTagContentState.visible) {
        SourceScreenSearchContentTagsRating(
            searchState = screenState.ratingTagContentState,
            screenEvent = screenEvent,
        )
    }

    TagsComponent(
        modifier = Modifier.fillMaxWidth(),
        state = screenState.tagsComponentState,
        factory = { tagUiState -> ChipItem(state = tagUiState, screenEvent = screenEvent) },
    )
}
