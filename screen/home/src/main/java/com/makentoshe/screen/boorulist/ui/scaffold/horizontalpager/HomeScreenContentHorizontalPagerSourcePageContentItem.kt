package com.makentoshe.screen.boorulist.ui.scaffold.horizontalpager

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.library.uikit.foundation.IndeterminateProgressBar
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.library.uikit.theme.error
import com.makentoshe.screen.boorulist.entity.SourceHealthUiState
import com.makentoshe.screen.boorulist.entity.SourceUiState

@Composable
internal fun HomeScreenContentHorizontalPagerSourcePageContentItem(
    sourceUiState: SourceUiState,
    onClick: () -> Unit,
) = Row(
    modifier = Modifier.fillMaxWidth().height(72.dp).clickable { onClick() },
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
) {
    HomeScreenContentHorizontalPagerSourcePageContentItemContent(sourceUiState)

    if (sourceUiState.healthState == SourceHealthUiState.Loading) {
        HomeScreenContentHorizontalPagerSourcePageContentItemContentHealthProgress()
    }
}

@Composable
private fun HomeScreenContentHorizontalPagerSourcePageContentItemContent(
    sourceUiState: SourceUiState,
) = Column(
    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
) {
    PrimaryText(text = sourceUiState.title)

    Spacer(modifier = Modifier.height(4.dp))

    when (sourceUiState.healthState) {
        SourceHealthUiState.Unavailable -> {
            val text = buildHostString(id = R.string.home_source_error, host = sourceUiState.host)
            SecondaryText(text = text, color = BooruchanTheme.colors.error)
        }

        SourceHealthUiState.Loading -> {
            val text = buildHostString(id = R.string.home_source_loading, host = sourceUiState.host)
            SecondaryText(text = text, color = BooruchanTheme.colors.opaque)
        }

        SourceHealthUiState.Available -> {
            val text = buildHostString(id = R.string.home_source_ok, host = sourceUiState.host)
            SecondaryText(text = text, color = BooruchanTheme.colors.dimmed)
        }
    }
}

@Composable
private fun buildHostString(@StringRes id: Int, host: String): String = if (host.isNotEmpty()) {
    stringResource(id = id, host)
} else {
    stringResource(id = R.string.home_source_host_empty)
}

@Composable
private fun HomeScreenContentHorizontalPagerSourcePageContentItemContentHealthProgress() = Box(
    modifier = Modifier.padding(24.dp),
    contentAlignment = Alignment.Center,
) {
    IndeterminateProgressBar(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
}
