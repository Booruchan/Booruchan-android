package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.library.uikit.foundation.ArrowBackIcon

@Composable
internal fun SourceScreenSearchBackIcon(
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(
    modifier = Modifier.size(48.dp).clickable {
        screenEvent(SourceScreenEvent.DismissSearch)
    },
    contentAlignment = Alignment.Center,
    content = { ArrowBackIcon() }
)
