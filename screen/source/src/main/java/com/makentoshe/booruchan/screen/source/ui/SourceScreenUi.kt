@file:OptIn(ExperimentalMaterialApi::class)

package com.makentoshe.booruchan.screen.source.ui

import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.ui.content.SourceScreenContent
import com.makentoshe.booruchan.screen.source.ui.search.SourceScreenSearch
import com.makentoshe.booruchan.screen.source.ui.topappbar.SourceScreenTopbar
import com.makentoshe.booruchan.screen.source.viewmodel.SnackbackState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.library.uikit.theme.transparent

@Composable
fun SourceScreenUi(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val backdropScaffoldState = rememberBackdropScaffoldState(initialValue = screenState.backdropValue)
    // Invoke backdrop state change on screen state change
    LaunchedEffect(key1 = screenState.backdropValue) {
        when (screenState.backdropValue) {
            BackdropValue.Concealed -> backdropScaffoldState.conceal()
            BackdropValue.Revealed -> backdropScaffoldState.reveal()
        }
    }

    // Send snackbar dismiss event to screen state
    LaunchedEffect(key1 = backdropScaffoldState.snackbarHostState.currentSnackbarData) {
        if (backdropScaffoldState.snackbarHostState.currentSnackbarData == null) {
            screenEvent(SourceScreenEvent.DismissSnackbar)
        }
    }

    // Send snackbar show event to screen state
    LaunchedEffect(key1 = screenState.snackbarState) {
        if (screenState.snackbarState is SnackbackState.Content) {
            backdropScaffoldState.snackbarHostState.showSnackbar(screenState.snackbarState.message)
        }
    }

    BackdropScaffold(
        scaffoldState = backdropScaffoldState,
        backLayerBackgroundColor = BooruchanTheme.colors.background,
        frontLayerBackgroundColor = BooruchanTheme.colors.background,
        frontLayerShape = BooruchanTheme.shapes.backdrop,
        frontLayerScrimColor = BooruchanTheme.colors.transparent,
        headerHeight = 0.dp,
        gesturesEnabled = false,
        appBar = {
            SourceScreenTopbar(screenState = screenState, screenEvent = screenEvent)
        },
        frontLayerContent = {
            SourceScreenSearch(screenState = screenState, screenEvent = screenEvent)
        },
        backLayerContent = {
            SourceScreenContent(screenState = screenState, screenEvent = screenEvent)
        }
    )
}
