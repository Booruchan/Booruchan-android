package com.makentoshe.booruchan.screen.source

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.makentoshe.booruchan.library.logging.screenLogInfo
import com.makentoshe.booruchan.library.navigation.SourceScreenNavigator
import com.makentoshe.booruchan.screen.Screen
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenViewModel

@Composable
fun SourceScreen(
    navigator: SourceScreenNavigator,
    sourceId: String,
) {
    val viewModel = hiltViewModel<SourceScreenViewModel>()
    val screenState by viewModel.stateFlow.collectAsState()

    // intialize viewmodel with screen argument
    LaunchedEffect(key1 = Unit) {
        viewModel.handleEvent(SourceScreenEvent.Initialize(sourceId = sourceId))
    }

    SourceScreenUi(screenState = screenState, viewModel = viewModel)

    screenLogInfo(Screen.Source, "SourceScreen composable")
}

@Composable
fun SourceScreenUi(
    screenState: SourceScreenState,
    viewModel: SourceScreenViewModel,
) {
    Text(screenState.toString())
}