package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.booruchan.screen.source.ui.component.ChipGroup
import com.makentoshe.booruchan.screen.source.ui.component.ChipItem
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenSearch(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(modifier = Modifier.fillMaxSize()) {
    SourceScreenSearchHeader(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        screenState = screenState,
        screenEvent = screenEvent,
    )

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)

    Box(modifier = Modifier.weight(1f)) {
        SourceScreenSearchContent(screenState = screenState, screenEvent = screenEvent)
    }

    Spacer(modifier = Modifier.size(16.dp))

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)

    SourceScreenSearchFooter(screenState = screenState, screenEvent = screenEvent)
}


@Composable
private fun SourceScreenSearchContent(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
) {
    SourceScreenSearchContentGeneral(screenState = screenState, screenEvent = screenEvent)
}

@Composable
private fun SourceScreenSearchContentGeneral(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val generalTags by remember(key1 = screenState.searchState) {
        mutableStateOf(screenState.searchState.generalTags)
    }

    if (generalTags.isNotEmpty()) {
        Spacer(modifier = Modifier.size(16.dp))

        SecondaryText(
            text = stringResource(id = R.string.source_search_tags_general),
            color = BooruchanTheme.colors.tag.general,
        )

        Spacer(modifier = Modifier.size(8.dp))

        ChipGroup(modifier = Modifier.fillMaxWidth()) {
            generalTags.forEach { tagUiState ->
                ChipItem(state = tagUiState, screenEvent = screenEvent)
            }
        }
    }
}
