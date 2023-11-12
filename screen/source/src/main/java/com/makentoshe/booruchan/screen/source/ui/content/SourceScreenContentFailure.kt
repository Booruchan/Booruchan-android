package com.makentoshe.booruchan.screen.source.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.component.DefaultErrorComponent

@Composable
fun SourceScreenContentFailure(
    title: String,
    description: String,
    button: String? = null,
    onClick: () -> Unit = {},
) = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    DefaultErrorComponent(title = title, description = description, button = button, onClick = onClick)
}

@Composable
fun SourceScreenContentFailure(
    throwable: Throwable,
    onClick: () -> Unit = {},
) = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    DefaultErrorComponent(throwable = throwable, onClick = onClick)
}