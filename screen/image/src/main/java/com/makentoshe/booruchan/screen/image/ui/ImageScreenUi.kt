package com.makentoshe.booruchan.screen.image.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenEvent
import com.makentoshe.booruchan.screen.image.viewmodel.ImageScreenState

@Composable
fun ImageScreenUi(
    screenState: ImageScreenState,
    screenEvent: (ImageScreenEvent) -> Unit,
) {
    Text(text = "$screenState")
}