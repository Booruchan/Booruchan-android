package com.makentoshe.booruchan.screen.image.ui.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState

@Composable
internal fun ImageScreenContentSuccess(
    contentState: ContentState.Content,
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) {
    Text(text = "$contentState")
}
