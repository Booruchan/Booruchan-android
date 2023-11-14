@file:OptIn(ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.source.ui.components.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.library.uikit.component.DefaultErrorComponent
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar

private const val StaggeredGridFooterKey = "SourceStaggeredGridFooterKey"

internal fun LazyStaggeredGridScope.staggeredGridFooterError(
    append: LoadState.Error,
    previewPostItems: LazyPagingItems<PreviewPostUiState>,
) = item(
    key = StaggeredGridFooterKey,
    span = StaggeredGridItemSpan.FullLine
) {
    DefaultErrorComponent(throwable = append.error) {
        previewPostItems.retry()
    }
}


internal fun LazyStaggeredGridScope.staggeredGridFooterLoading() = item(
    key = StaggeredGridFooterKey,
    span = StaggeredGridItemSpan.FullLine,
) {
    Column(
        modifier = Modifier.fillMaxWidth().height(128.dp).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = { IndeterminateProgressBar() }
    )
}
