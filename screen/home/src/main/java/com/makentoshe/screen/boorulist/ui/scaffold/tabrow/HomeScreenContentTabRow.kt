package com.makentoshe.screen.boorulist.ui.scaffold.tabrow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreenContentTabRow(
    screenState: HomeScreenState,
    pagerState: PagerState,
) = PrimaryTabRow(
    selectedTabIndex = pagerState.currentPage,
    modifier = Modifier.fillMaxWidth(),
    containerColor = BooruchanTheme.colors.background,
    contentColor = BooruchanTheme.colors.foreground,
    indicator = { tabPositions ->
        HomeScreenContentTabRowIndicator(tabPosition = tabPositions[pagerState.currentPage])
    },
    tabs = {
        HomeScreenContentTabRowTabs(screenState = screenState, pagerState = pagerState)
    }
)

