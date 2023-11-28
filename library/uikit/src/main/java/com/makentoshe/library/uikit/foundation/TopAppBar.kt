package com.makentoshe.library.uikit.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun SmallTopAppBar(
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) = Row(
    modifier = Modifier.fillMaxWidth().height(56.dp)
        .background(color = BooruchanTheme.colors.background)
        .padding(horizontal = 4.dp),
) {
    if (navigationIcon == null) {
        Spacer(modifier = Modifier.width(12.dp))
    } else {
        Row(
            modifier = Modifier.fillMaxHeight().width(68.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = { navigationIcon() },
        )
    }

    Row(
        modifier = Modifier.fillMaxHeight().weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        content = { ProvideTextStyle(value = BooruchanTheme.typography.titleText, content = title) },
    )

    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        content = actions,
    )
}