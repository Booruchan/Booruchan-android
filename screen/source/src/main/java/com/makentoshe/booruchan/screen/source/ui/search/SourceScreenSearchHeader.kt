@file:OptIn(ExperimentalComposeUiApi::class)

package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.makentoshe.booruchan.screen.source.ui.component.SearchTextField
import com.makentoshe.booruchan.screen.source.viewmodel.AutocompleteState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.PrimaryText

@Composable
internal fun SourceScreenSearchHeader(
    modifier: Modifier,
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(modifier = modifier) {
    var searchTextFieldFocus by remember { mutableStateOf(false) }
    val searchTextFieldLabelString by remember(key1 = screenState.searchState.value, key2 = searchTextFieldFocus) {
        if (screenState.searchState.value.isEmpty() && !searchTextFieldFocus) {
            mutableStateOf(screenState.searchState.label)
        } else {
            mutableStateOf(null)
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    SearchTextField(
        modifier = Modifier.fillMaxWidth().onFocusChanged { searchTextFieldFocus = it.hasFocus },
        value = screenState.searchState.value,
        onValueChange = {
            screenEvent(SourceScreenEvent.SearchValueChange(value = it))
        },
        label = searchTextFieldLabelString?.let { { PrimaryText(text = it) } },
        keyboardActions = KeyboardActions(
            onSearch = {
                screenEvent(SourceScreenEvent.SearchTagAdd(tag = screenState.searchState.value))
                screenEvent(SourceScreenEvent.SearchValueChange(value = ""))
            },
            onDone = {
                keyboardController?.hide()
            }
        )
    )

    var dropdownMenuExpanded by remember(key1 = screenState.searchState.autocompleteState) {
        mutableStateOf(screenState.searchState.autocompleteState is AutocompleteState.Content)
    }

    DropdownMenu(
        modifier = Modifier.fillMaxWidth().height(256.dp),
        expanded = dropdownMenuExpanded,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        onDismissRequest = { dropdownMenuExpanded = false },
    ) {
        (screenState.searchState.autocompleteState as? AutocompleteState.Content)?.autocomplete?.forEachIndexed { _, state ->
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = { Text(text = state.title) },
                onClick = {
                    screenEvent(SourceScreenEvent.SearchTagAdd(state.value))
                },
            )
        }
    }
}