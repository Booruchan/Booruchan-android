@file:OptIn(ExperimentalMaterialApi::class)

package com.makentoshe.booruchan.screen.source.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.source.ui.content.SourceScreenContent
import com.makentoshe.booruchan.screen.source.ui.search.SourceScreenSearch
import com.makentoshe.booruchan.screen.source.ui.topappbar.SourceScreenTopbar
import com.makentoshe.booruchan.screen.source.viewmodel.SnackbackState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun SourceScreenUi(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(modifier = Modifier.fillMaxSize()) {

    val scaffoldState = rememberScaffoldState()

    // Send snackbar dismiss event to screen state
    LaunchedEffect(key1 = scaffoldState.snackbarHostState.currentSnackbarData) {
        if (scaffoldState.snackbarHostState.currentSnackbarData == null) {
            screenEvent(SourceScreenEvent.DismissSnackbar)
        }
    }

    // Send snackbar show event to screen state
    LaunchedEffect(key1 = screenState.snackbarState) {
        if (screenState.snackbarState is SnackbackState.Content) {
            scaffoldState.snackbarHostState.showSnackbar(screenState.snackbarState.message)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        backgroundColor = BooruchanTheme.colors.background,
        topBar = {
            SourceScreenTopbar(screenState = screenState, screenEvent = screenEvent)
        },
        drawerGesturesEnabled = false,
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                SourceScreenContent(screenState = screenState, screenEvent = screenEvent)
            }
        }
    )

    SourceScreenSearch(
        screenState = screenState,
        screenEvent = screenEvent,
    )
}
