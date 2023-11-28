@file:OptIn(ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.image.ui.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.image.ui.page.ImageScreenPageSampleImage
import com.makentoshe.booruchan.screen.image.ui.page.ImageScreenPageSampleInfo
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState

@Composable
internal fun ImageScreenContentSuccess(
    contentState: ContentState.Content,
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) {
    val state = rememberPagerState { 2 }

    VerticalPager(
        modifier = Modifier.fillMaxSize(),
        state = state,
        flingBehavior = PagerDefaults.flingBehavior(state = state, snapPositionalThreshold = 0.1f)
    ) { page ->
        when (page) {
            0 -> ImageScreenPageSampleImage(contentState = contentState)
            else -> ImageScreenPageSampleInfo(contentState = contentState)
        }
    }
}
