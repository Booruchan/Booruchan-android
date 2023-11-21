package com.makentoshe.booruchan.screen.source.ui.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.makentoshe.booruchan.screen.source.ui.components.grid.StaggeredGrid
import com.makentoshe.booruchan.screen.source.viewmodel.ContentState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState

@Composable
internal fun SourceScreenContentSuccess(
    screenState: SourceScreenState,
    contentState: ContentState.Success,
    screenEvent: (SourceScreenEvent) -> Unit,
) {
    val previewPostItems = contentState.pagerFlow.collectAsLazyPagingItems()

    when (val refreshState = previewPostItems.loadState.refresh) {
        // Show fullscreen loading state only when content is empty
        // If content is already loaded and have items and refresh occurs
        // we don't need to show fullscreen loading
        is LoadState.Loading -> {
            if (previewPostItems.itemSnapshotList.isEmpty()) {
                SourceScreenContentLoading()
            } else {
                StaggeredGrid(previewPostItems = previewPostItems, screenEvent = screenEvent)
            }
        }

        // Show fullscreen error state only when content is empty
        // If content is already loaded we shows a snackbar instead
        is LoadState.Error -> {
            if (previewPostItems.itemSnapshotList.isEmpty()) {
                SourceScreenContentFailure(throwable = refreshState.error) {
                    previewPostItems.refresh()
                }
            } else {
                LaunchedEffect(key1 = Unit) {
                    screenEvent(SourceScreenEvent.ShowSnackbar(throwable = refreshState.error))
                }
                StaggeredGrid(previewPostItems = previewPostItems, screenEvent = screenEvent)
            }
        }

        else -> {
            StaggeredGrid(previewPostItems = previewPostItems, screenEvent = screenEvent)
        }
    }
}

