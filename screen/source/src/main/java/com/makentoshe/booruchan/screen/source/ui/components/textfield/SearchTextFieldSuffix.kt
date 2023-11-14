package com.makentoshe.booruchan.screen.source.ui.components.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.AutocompleteState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.CloseIcon
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar

@Composable
internal fun SearchTextFieldSuffix(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(modifier = Modifier.size(36.dp), contentAlignment = Alignment.Center) {
    if (screenState.searchState.autocompleteState is AutocompleteState.Loading) {
        IndeterminateProgressBar(modifier = Modifier.padding(4.dp), strokeWidth = 3.dp)
    }

    if (screenState.searchState.value.isNotEmpty()) {
        CloseIcon(modifier = Modifier.clickable {
            screenEvent(SourceScreenEvent.SearchValueChange(value = ""))
        })
    }
}
