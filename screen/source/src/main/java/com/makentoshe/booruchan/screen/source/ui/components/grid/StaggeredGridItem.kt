package com.makentoshe.booruchan.screen.source.ui.components.grid

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun StaggeredGridItem(
    item: PreviewPostUiState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(
    modifier = Modifier
        .aspectRatio(1 / item.hwRatio, true)
        .sizeIn(minWidth = 128.dp, minHeight = 128.dp)
        .border(width = 1.dp, color = BooruchanTheme.colors.separator)
        .clickable { screenEvent(SourceScreenEvent.NavigationImage(item.id)) }
) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(item.url)
        .size(Size.ORIGINAL)
        .build()

    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = request,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        loading = {
            IndeterminateProgressBar(modifier = Modifier.wrapContentSize())
        }
    )
}
