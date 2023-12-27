package com.makentoshe.booruchan.screen.image.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
internal fun ImageScreenPageSampleImage(
    contentState: ContentState.Content,
) = Box(
    modifier = Modifier.fillMaxSize(),
) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(contentState.samplePostImageState.url)
        .size(Size.ORIGINAL)
        .build()

    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxSize().zoomable(rememberZoomState()),
        model = request,
        contentScale = ContentScale.Fit,
        contentDescription = null,
        loading = {
            IndeterminateProgressBar(modifier = Modifier.wrapContentSize())
        }
    )
}
