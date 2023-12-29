package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenRatingTagContentState
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonRow

@Composable
internal fun SourceScreenSearchContentTagsRating(
    searchState: SourceScreenRatingTagContentState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    Spacer(modifier = Modifier.size(16.dp))

    TagsRatingSegmentedButtonRow(
        state = searchState.ratingTagSegmentedButtonState,
        enabled = true,
        onClick = { it, _ -> screenEvent(SourceScreenEvent.SearchTagChangeRating(it)) },
    )
}
