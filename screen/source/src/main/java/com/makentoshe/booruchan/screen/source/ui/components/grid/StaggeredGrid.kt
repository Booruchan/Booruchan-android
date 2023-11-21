@file:OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.source.ui.components.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent

@Composable
internal fun StaggeredGrid(
    previewPostItems: LazyPagingItems<PreviewPostUiState>,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val refreshing by remember(key1 = previewPostItems.loadState.refresh) {
        mutableStateOf(previewPostItems.loadState.refresh is LoadState.Loading)
    }
    val pullRefreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh = { previewPostItems.refresh() })

    Box(
        modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            columns = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(top = 12.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            staggeredGridItems(items = previewPostItems, screenEvent = screenEvent)

            when (val append = previewPostItems.loadState.append) {
                is LoadState.Loading -> {
                    staggeredGridFooterLoading()
                }

                is LoadState.Error -> {
                    staggeredGridFooterError(append = append, previewPostItems = previewPostItems)
                }

                else -> {
                    val refresh = previewPostItems.loadState.refresh
                    val append = previewPostItems.loadState.append

                    if (append.endOfPaginationReached) {
                        staggeredGridFinished()
                    }

                    if (refresh is LoadState.Error) {
                        staggeredGridFooterError(append = refresh, previewPostItems = previewPostItems)
                    }
                }
            }
        }

        PullRefreshIndicator(refreshing = refreshing, state = pullRefreshState)
    }
}
