package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.booruchan.screen.source.ui.components.chip.ChipItem
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenArtistTagsContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenCharacterTagsContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenCopyrightTagsContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenGeneralTagsContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.ChipGroup
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

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

    // Show copyright tags only if there are any tags to display
    if (screenState.copyrightTagsContentState.visible) {
        SourceScreenSearchContentTagsCopyright(
            copyrightTagsContentState = screenState.copyrightTagsContentState,
            screenEvent = screenEvent,
        )
    }

    // Show character tags only if there are any tags to display
    if (screenState.characterTagsContentState.visible) {
        SourceScreenSearchContentTagsCharacter(
            characterTagsContentState = screenState.characterTagsContentState,
            screenEvent = screenEvent,
        )
    }

    // Show artist tags only if there are any tags to display
    if (screenState.artistTagsContentState.visible) {
        SourceScreenSearchContentTagsArtist(
            artistTagsContentState = screenState.artistTagsContentState,
            screenEvent = screenEvent,
        )
    }

    // Show general tags only if there are any tags to display
    if (screenState.generalTagsContentState.visible) {
        SourceScreenSearchContentTagsGeneral(
            generalTagsContentState = screenState.generalTagsContentState,
            screenEvent = screenEvent,
        )
    }

}


@Composable
private fun SourceScreenSearchContentTagsGeneral(
    generalTagsContentState: SourceScreenGeneralTagsContentState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val generalTags by remember(key1 = generalTagsContentState) {
        mutableStateOf(generalTagsContentState.tags)
    }

    Spacer(modifier = Modifier.size(16.dp))

    SmallText(
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

@Composable
private fun SourceScreenSearchContentTagsCharacter(
    characterTagsContentState: SourceScreenCharacterTagsContentState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = SourceScreenSearchContentTagsCommon(
    label = {
        SmallText(
            text = stringResource(id = R.string.component_tags_character_title),
            color = BooruchanTheme.colors.tag.artist,
        )
    },
    content = {
        characterTagsContentState.tags.forEach { tagUiState ->
            ChipItem(state = tagUiState, screenEvent = screenEvent)
        }
    },
)

@Composable
private fun SourceScreenSearchContentTagsArtist(
    artistTagsContentState: SourceScreenArtistTagsContentState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = SourceScreenSearchContentTagsCommon(
    label = {
        SmallText(
            text = stringResource(id = R.string.component_tags_artist_title),
            color = BooruchanTheme.colors.tag.artist,
        )
    },
    content = {
        artistTagsContentState.tags.forEach { tagUiState ->
            ChipItem(state = tagUiState, screenEvent = screenEvent)
        }
    },
)

@Composable
private fun SourceScreenSearchContentTagsCopyright(
    copyrightTagsContentState: SourceScreenCopyrightTagsContentState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = SourceScreenSearchContentTagsCommon(
    label = {
        SmallText(
            text = stringResource(id = R.string.component_tags_copyright_title),
            color = BooruchanTheme.colors.tag.copyright,
        )
    },
    content = {
        copyrightTagsContentState.tags.forEach { tagUiState ->
            ChipItem(state = tagUiState, screenEvent = screenEvent)
        }
    },
)

@Composable
private fun SourceScreenSearchContentTagsCommon(
    label: @Composable () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Spacer(modifier = Modifier.size(16.dp))

    label()

    Spacer(modifier = Modifier.size(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        content = content,
    )
}
