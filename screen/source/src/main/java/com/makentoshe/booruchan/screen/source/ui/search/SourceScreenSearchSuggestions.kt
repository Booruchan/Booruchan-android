package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.source.viewmodel.AutocompleteState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenSearchSuggestions(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = when (val autocompleteState = screenState.searchState.autocompleteState) {
    is AutocompleteState.Content -> SourceScreenSearchSuggestionsContent(
        autocompleteState = autocompleteState,
        screenEvent = screenEvent,
    )

    else -> Unit
}

@Composable
private fun SourceScreenSearchSuggestionsContent(
    autocompleteState: AutocompleteState.Content,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(
    modifier = Modifier.fillMaxSize()
        .background(BooruchanTheme.colors.dashboard)
        .horizontalScroll(state = rememberScrollState()),
) {
    autocompleteState.autocomplete.forEach { autocompleteUiState ->
        DropdownMenuItem(
            modifier = Modifier.fillMaxWidth(),
            text = {
                PrimaryText(text = autocompleteUiState.title)
            },
            onClick = {
                screenEvent(SourceScreenEvent.SuggestedItemClicked(autocompleteUiState.value))
            },
        )
    }
}
