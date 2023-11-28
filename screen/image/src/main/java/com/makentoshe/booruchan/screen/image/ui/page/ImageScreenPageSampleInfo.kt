package com.makentoshe.booruchan.screen.image.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState

@Composable
internal fun ImageScreenPageSampleInfo(
    contentState: ContentState.Content,
) = Box(
    modifier = Modifier.fillMaxSize(),
) {
    Text(text = "$contentState")
}
