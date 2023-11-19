@file:OptIn(ExperimentalComposeUiApi::class)

package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.library.uikit.theme.transparent

@Composable
internal fun SourceScreenSearchTopAppBar(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = TopAppBar(
    modifier = Modifier.fillMaxWidth().height(56.dp),
    elevation = 0.dp,
    backgroundColor = BooruchanTheme.colors.dashboard,
    contentColor = BooruchanTheme.colors.foreground,
) {
    SourceScreenSearchBackIcon(screenEvent = screenEvent)
    Box(modifier = Modifier.weight(1.0f, fill = true)) {
        SourceScreenSearchTextField(screenState = screenState, screenEvent = screenEvent)
    }
    SourceScreenSearchCloseIcon(screenState = screenState, screenEvent = screenEvent)
}

@Composable
internal fun SourceScreenSearchTextField(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember(key1 = Unit) { FocusRequester() }

    TextField(
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester).clipToBounds(),
        value = screenState.searchState.value,
        onValueChange = {
            screenEvent(SourceScreenEvent.SearchValueChange(value = it))
        },
        placeholder = {
            PrimaryText(text = screenState.searchState.label)
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            cursorColor = BooruchanTheme.colors.foreground,
            focusedTextColor = BooruchanTheme.colors.foreground,
            unfocusedTextColor = BooruchanTheme.colors.foreground,
            focusedContainerColor = BooruchanTheme.colors.transparent,
            unfocusedContainerColor = BooruchanTheme.colors.transparent,
            focusedIndicatorColor = BooruchanTheme.colors.transparent,
            unfocusedIndicatorColor = BooruchanTheme.colors.transparent,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (screenState.searchState.value.isEmpty()) {
                    screenEvent(SourceScreenEvent.SearchApplyFilters)
                    keyboardController?.hide()
                    screenEvent(SourceScreenEvent.DismissSearch)
                } else {
                    screenEvent(SourceScreenEvent.SearchTagAdd(tag = screenState.searchState.value))
                    screenEvent(SourceScreenEvent.SearchValueChange(value = ""))
                }
            },
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    )

    // Show keyboard by focusing text field
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
}
