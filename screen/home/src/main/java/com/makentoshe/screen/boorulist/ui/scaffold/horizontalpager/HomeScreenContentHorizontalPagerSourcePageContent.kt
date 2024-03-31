package com.makentoshe.screen.boorulist.ui.scaffold.horizontalpager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenEvent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenSourcePageContent
import com.makentoshe.screen.boorulist.viewmodel.HomeScreenState

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreenContentHorizontalPagerSourcePageContent(
    screenState: HomeScreenState,
    screenEvent: (HomeScreenEvent) -> Unit,
    pageContentState: HomeScreenSourcePageContent.Content,
) {
    var refreshing by remember(key1 = pageContentState) { mutableStateOf(pageContentState.refreshing) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshing = true
            screenEvent(HomeScreenEvent.RefreshPlugins)
        },
    )

    Box(
        modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pageContentState.sources) { source ->
                HomeScreenContentHorizontalPagerSourcePageContentItem(
                    sourceUiState = source,
                    onClick = { screenEvent(HomeScreenEvent.NavigationSource(sourceId = source.id)) },
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = BooruchanTheme.colors.separator,
                    thickness = 1.dp,
                )
            }
        }

        PullRefreshIndicator(refreshing = refreshing, state = pullRefreshState)
    }
}
