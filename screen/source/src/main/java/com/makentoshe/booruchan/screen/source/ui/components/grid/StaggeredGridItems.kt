@file:OptIn(ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.source.ui.components.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.paging.compose.LazyPagingItems
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState

internal fun LazyStaggeredGridScope.staggeredGridItems(
    items: LazyPagingItems<PreviewPostUiState>
) = items(
    count = items.itemCount,
) { index ->
    val item = items[index]
    if (item != null) { StaggeredGridItem(item = item) }
}

