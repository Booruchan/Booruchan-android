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
import androidx.compose.material3.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.makentoshe.booruchan.screen.source.R
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.library.uikit.component.DefaultErrorComponent
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

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

internal fun LazyStaggeredGridScope.staggeredGridFinished() = item(
    key = StaggeredGridFooterKey,
    span = StaggeredGridItemSpan.FullLine
) {
    Column(
        modifier = Modifier.fillMaxWidth().height(162.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = BooruchanTheme.colors.separator,
        )
        SecondaryText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.source_content_finished_text),
            textAlign = TextAlign.Center,
        )
    }
}
