package com.makentoshe.booruchan.screen.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.makentoshe.booruchan.library.logging.screenLogInfo
import com.makentoshe.booruchan.library.navigation.DrawerScreenNavigator
import com.makentoshe.booruchan.screen.drawer.viewmodel.DrawerScreenViewModel
import com.makentoshe.library.uikit.extensions.collectLatestInComposable

@Composable
fun DrawerScreen(navigator: DrawerScreenNavigator) {
    val viewModel = hiltViewModel<DrawerScreenViewModel>()
    val screenState by viewModel.stateFlow.collectAsState()

    viewModel.navigationFlow.collectLatestInComposable { destination ->
        screenLogInfo("Drawer", "Navigation destination: $destination")
        // navigator.
    }

    DrawerScreenUi(screenState = screenState, screenEvent = viewModel::handleEvent)
}


