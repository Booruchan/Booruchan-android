package com.makentoshe.screen.boorulist.ui.scaffold.horizontalpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenEvent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreenContentHorizontalPager(
    screenState: HomeScreenState,
    screenEvent: (HomeScreenEvent) -> Unit,
    pagerState: PagerState,
) = HorizontalPager(state = pagerState) { page ->
    Box(modifier = Modifier.fillMaxSize()) {
        when (page) {
            0 -> HomeScreenContentHorizontalPagerSourcePage(screenState = screenState, screenEvent)
            else -> Unit
        }
    }
}
