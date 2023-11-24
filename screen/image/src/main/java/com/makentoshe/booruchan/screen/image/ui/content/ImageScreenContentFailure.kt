package com.makentoshe.booruchan.screen.image.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.library.uikit.component.DefaultErrorComponent

@Composable
internal fun ImageScreenContentFailure(
    contentState: ContentState.Failure,
    screenEvent: (ImageScreenEvent) -> Unit,
) = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    DefaultErrorComponent(
        title = contentState.title,
        description = contentState.description,
        button = contentState.button,
        onClick = { screenEvent(contentState.event) },
    )
}
