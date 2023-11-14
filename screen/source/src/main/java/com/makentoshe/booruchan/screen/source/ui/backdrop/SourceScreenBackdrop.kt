package com.makentoshe.booruchan.screen.source.ui.backdrop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenBackdrop(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(modifier = Modifier.fillMaxSize()) {
    SourceScreenBackdropHeader(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        screenState = screenState,
        screenEvent = screenEvent,
    )

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)

    Box(modifier = Modifier.weight(1f)) {
        SourceScreenBackdropContent(screenState = screenState, screenEvent = screenEvent)
    }

    Spacer(modifier = Modifier.size(16.dp))

    Divider(modifier = Modifier.fillMaxWidth(), color = BooruchanTheme.colors.separator)

    SourceScreenBackdropFooter(screenEvent = screenEvent)
}
