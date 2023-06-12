package com.makentoshe.booruchan.screen.boorucontent.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.booruchan.screen.boorucontent.viewmodel.BoorucontentEvent
import com.makentoshe.booruchan.screen.boorucontent.viewmodel.BoorucontentState
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun BoorucontentScreenUi(
    state: BoorucontentState,
    event: (BoorucontentEvent) -> Unit,
) = Scaffold(
    topBar = { BoorucontentTopBar(state, event = event) },
    containerColor = BooruchanTheme.colors.background,
) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
        BoorucontentContent(state = state)
    }
}

