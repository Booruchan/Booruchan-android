package com.makentoshe.screen.boorulist.ui.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.library.uikit.foundation.TitleText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun HomeScreenTopAppBar() = Column(
    modifier = Modifier.fillMaxWidth().background(color = BooruchanTheme.colors.background),
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(56.dp).padding(start = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        TitleText(text = stringResource(id = R.string.app_name))
    }
}
