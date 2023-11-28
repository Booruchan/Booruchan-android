package com.makentoshe.booruchan.screen.image

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.makentoshe.booruchan.library.logging.screenLogInfo
import com.makentoshe.booruchan.library.navigation.ImageScreenNavigator
import com.makentoshe.booruchan.screen.Screen
import com.makentoshe.booruchan.screen.image.ui.ImageScreenUi
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenDestination
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenViewModel
import com.makentoshe.library.uikit.extensions.collectLatestInComposable

@Composable
fun ImageScreen(
    sourceId: String,
    postId: String,
    navigator: ImageScreenNavigator,
) {
    val viewModel = hiltViewModel<ImageScreenViewModel>()
    val screenState by viewModel.stateFlow.collectAsState()

    // navigate to a destination from viewmodel
    viewModel.navigationFlow.collectLatestInComposable { destination ->
        screenLogInfo(Screen.Source, "Navigation destination: $destination")
        when (destination) {
            ImageScreenDestination.BackDestination -> {
                navigator.back()
            }
        }
    }

    // intialize viewmodel with screen argument
    LaunchedEffect(key1 = Unit) {
        viewModel.handleEvent(ImageScreenEvent.Initialize(sourceId = sourceId, postId = postId))
    }

    ImageScreenUi(screenState = screenState, screenEvent = viewModel::handleEvent)
}