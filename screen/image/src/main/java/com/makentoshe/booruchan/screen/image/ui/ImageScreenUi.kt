package com.makentoshe.booruchan.screen.image.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.image.ui.content.ImageScreenContent
import com.makentoshe.booruchan.screen.image.ui.topappbar.ImageScreenTopAppBar
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun ImageScreenUi(
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) = Box(modifier = Modifier.fillMaxSize()) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BooruchanTheme.colors.background,
        contentColor = BooruchanTheme.colors.foreground,
        topBar = { ImageScreenTopAppBar(screenState = screenState, screenEvent = screenEvent) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                ImageScreenContent(screenState = screenState, screenEvent = screenEvent)
            }
        }
    )
}