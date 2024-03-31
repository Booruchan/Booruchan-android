package com.makentoshe.screen.boorulist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.screen.boorulist.ui.scaffold.HomeScreenContent
import com.makentoshe.screen.boorulist.ui.topappbar.HomeScreenTopAppBar
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenEvent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState

@Composable
internal fun HomeScreenUi(
    state: HomeScreenState,
    event: (HomeScreenEvent) -> Unit,
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = BooruchanTheme.colors.background,
    topBar = { HomeScreenTopAppBar() },
    floatingActionButton = { HomeScreenFloatingActionButton(state, event) },
    content = { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            HomeScreenContent(screenState = state, screenEvent = event)
        }
    }
)

@Composable
internal fun HomeScreenFloatingActionButton(
    screenState: HomeScreenState,
    screenEvent: (HomeScreenEvent) -> Unit,
) {
    ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
        Text(text = screenState.floatingButtonText)
    }
}
