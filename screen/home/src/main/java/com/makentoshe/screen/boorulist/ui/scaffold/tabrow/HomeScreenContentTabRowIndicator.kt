package com.makentoshe.screen.boorulist.ui.scaffold.tabrow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun HomeScreenContentTabRowIndicator(
    tabPosition: TabPosition,
) = TabRowDefaults.SecondaryIndicator(
    modifier = Modifier.tabIndicatorOffset(tabPosition).fillMaxWidth(),
    color = BooruchanTheme.colors.foreground,
)
