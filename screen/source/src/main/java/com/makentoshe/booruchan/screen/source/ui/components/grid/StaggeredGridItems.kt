package com.makentoshe.booruchan.screen.source.ui.components.grid

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.paging.compose.LazyPagingItems
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent

internal fun LazyStaggeredGridScope.staggeredGridItems(
    items: LazyPagingItems<PreviewPostUiState>,
    screenEvent: (SourceScreenEvent) -> Unit,
) = items(
    count = items.itemCount,
) { index ->
    val item = items[index]
    if (item != null) { StaggeredGridItem(item = item, screenEvent = screenEvent) }
}

