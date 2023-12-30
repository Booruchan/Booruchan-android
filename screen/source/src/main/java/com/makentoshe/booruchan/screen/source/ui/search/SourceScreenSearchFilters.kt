package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.ui.components.chip.TagChipItem
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.component.rating.RatingComponent
import com.makentoshe.library.uikit.component.tags.TagsComponent

@Composable
internal fun SourceScreenSearchFilters(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
) {
    RatingComponent(
        modifier = Modifier.fillMaxWidth(),
        state = screenState.ratingComponentState,
        onClick = { it, _ -> screenEvent(SourceScreenEvent.SearchTagChangeRating(it)) },
    )

    TagsComponent(
        modifier = Modifier.fillMaxWidth(),
        state = screenState.tagsComponentState,
        factory = { tagUiState -> TagChipItem(state = tagUiState, screenEvent = screenEvent) },
    )
}
