@file:OptIn(ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.image.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.makentoshe.booruchan.screen.image.ui.content.ImageScreenContent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun ImageScreenUi(
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) = Box(modifier = Modifier.fillMaxSize()) {
    val verticalPagerState = rememberPagerState(initialPage = 1) { 3 }

    val screenAlpha by remember(verticalPagerState.currentPageOffsetFraction) {
        if (verticalPagerState.currentPage == 0) return@remember mutableFloatStateOf(0f)
        mutableFloatStateOf(1 - (-verticalPagerState.currentPageOffsetFraction * 2))
    }

    LaunchedEffect(key1 = verticalPagerState.currentPage) {
        if (verticalPagerState.currentPage == 0) screenEvent(ImageScreenEvent.NavigationBack)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().alpha(screenAlpha),
        containerColor = BooruchanTheme.colors.background,
        contentColor = BooruchanTheme.colors.foreground,
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                ImageScreenContent(
                    screenState = screenState,
                    screenEvent = screenEvent,
                    verticalPagerState = verticalPagerState,
                )
            }
        }
    )
}
