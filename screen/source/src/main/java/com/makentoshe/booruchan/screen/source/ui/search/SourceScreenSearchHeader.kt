@file:OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)

package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
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
import com.makentoshe.booruchan.screen.source.ui.component.SearchTextField
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.PrimaryText

@Composable
fun SourceScreenSearchHeader(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
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
}
