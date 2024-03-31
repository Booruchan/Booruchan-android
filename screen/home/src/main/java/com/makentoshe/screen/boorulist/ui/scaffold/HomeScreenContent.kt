package com.makentoshe.screen.boorulist.ui.scaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.screen.boorulist.ui.scaffold.horizontalpager.HomeScreenContentHorizontalPager
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenEvent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState
import com.makentoshe.screen.boorulist.viewmodel.tabs

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreenContent(
    screenState: HomeScreenState,
    screenEvent: (HomeScreenEvent) -> Unit,
) = Column(modifier = Modifier.fillMaxSize()) {
    val pagerState = rememberPagerState(pageCount = { screenState.tabs.count() })

//    HomeScreenContentTabRow(screenState = screenState, pagerState = pagerState)
    HomeScreenContentHorizontalPager(screenState = screenState, screenEvent = screenEvent, pagerState = pagerState)
}

