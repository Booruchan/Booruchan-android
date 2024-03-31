package com.makentoshe.screen.boorulist.ui.scaffold.tabrow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState
import com.makentoshe.screen.boorulist.viewmodel.tabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreenContentTabRowTabs(
    screenState: HomeScreenState,
    pagerState: PagerState,
) = screenState.tabs.forEachIndexed { index, s ->
    HomeScreenContentTabRowTabItem(text = s, index = index, pagerState = pagerState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContentTabRowTabItem(
    text: String,
    index: Int,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    Tab(
        selected = pagerState.currentPage == index,
        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
        text = { Text(text = text) },
        selectedContentColor = BooruchanTheme.colors.foreground,
        unselectedContentColor = BooruchanTheme.colors.dimmed,
    )
}
