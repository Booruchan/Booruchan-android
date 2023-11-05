@file:OptIn(ExperimentalComposeUiApi::class)

package com.makentoshe.booruchan.screen.source.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState
import com.makentoshe.library.uikit.foundation.SecondaryTextBold
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun SourceScreenSearchFooter(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = Column(modifier = Modifier.padding(16.dp)) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BooruchanTheme.colors.opaque),
        onClick = {
            keyboardController?.hide()
            screenEvent(SourceScreenEvent.SearchApplyFilters)
        },
    ) {
        SecondaryTextBold(
            color = BooruchanTheme.colors.foreground,
            text = stringResource(id = R.string.source_search_apply_filters_button),
        )
    }
}
