package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.booruchan.screen.source.ui.components.chip.ChipItem
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenSearchState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.ChipGroup
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenSearchContentTags(
    searchState: SourceScreenSearchState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
) {
    // Show rating tag selector only if there are any tags to select
    if (searchState.ratingTagSegmentedButtonState.values.isNotEmpty()) {
        SourceScreenSearchContentTagsRating(searchState = searchState, screenEvent = screenEvent)
    }

    SourceScreenSearchContentTagsGeneral(searchState = searchState, screenEvent = screenEvent)
}


@Composable
private fun SourceScreenSearchContentTagsGeneral(
    searchState: SourceScreenSearchState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val generalTags by remember(key1 = searchState) {
        mutableStateOf(searchState.generalTags)
    }

    if (generalTags.isNotEmpty()) {
        Spacer(modifier = Modifier.size(16.dp))

        SecondaryText(
            text = stringResource(id = R.string.source_search_tags_general),
            color = BooruchanTheme.colors.foreground,
        )

        Spacer(modifier = Modifier.size(8.dp))

        ChipGroup(modifier = Modifier.fillMaxWidth()) {
            generalTags.forEach { tagUiState ->
                ChipItem(state = tagUiState, screenEvent = screenEvent)
            }
        }
    }
}
