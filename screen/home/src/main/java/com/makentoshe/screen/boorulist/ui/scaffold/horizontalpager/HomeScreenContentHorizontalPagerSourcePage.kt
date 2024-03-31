package com.makentoshe.screen.boorulist.ui.scaffold.horizontalpager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenEvent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenSourcePageContent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState

@Composable
internal fun HomeScreenContentHorizontalPagerSourcePage(
    screenState: HomeScreenState,
    screenEvent: (HomeScreenEvent) -> Unit,
) = when (screenState.sourcePageState.content) {
    is HomeScreenSourcePageContent.Content -> HomeScreenContentHorizontalPagerSourcePageContent(
        screenState = screenState,
        screenEvent = screenEvent,
        pageContentState = screenState.sourcePageState.content,
    )

    HomeScreenSourcePageContent.Loading -> HomeScreenContentHorizontalPagerSourcePageLoading()
    HomeScreenSourcePageContent.None -> HomeScreenContentHorizontalPagerSourcePageLoading()
}
