@file:OptIn(ExperimentalFoundationApi::class)

package com.makentoshe.booruchan.screen.image.ui.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
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
) = VerticalPager(state = rememberPagerState { 2 }) { page ->
    when (page) {
        0 -> ImageScreenPageSampleImage(contentState = contentState)
        else -> ImageScreenPageSampleInfo(contentState = contentState)
    }
}