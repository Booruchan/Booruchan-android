package com.makentoshe.booruchan.screen.source.ui.topappbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.library.uikit.foundation.MagnifyIcon

@Composable
internal fun SourceScreenTopbarActionIcon(
    screenEvent: (SourceScreenEvent) -> Unit,
) = Box(
    modifier = Modifier.size(48.dp).clickable {
        screenEvent(SourceScreenEvent.NavigationBackdrop)
    },
    contentAlignment = Alignment.Center,
    content = { MagnifyIcon() }
)
