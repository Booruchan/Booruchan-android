package com.makentoshe.library.uikit.component.tags

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.library.uikit.foundation.ChipGroup

@Composable
fun TagsLayout(
    label: @Composable () -> Unit,
    content: @Composable () -> Unit,
) = Column {
    Spacer(modifier = Modifier.size(16.dp))
    label()
    Spacer(modifier = Modifier.size(2.dp))
    content()
}

@Composable
fun TagsWithRowLayout(
    label: @Composable () -> Unit,
    content: @Composable RowScope.() -> Unit,
) = TagsLayout(label = label) {
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        content = content,
    )
}

@Composable
fun TagsWithGroupLayout(
    label: @Composable () -> Unit,
    content: @Composable RowScope.() -> Unit,
) = TagsLayout(label = label) {
    ChipGroup(
        modifier = Modifier.fillMaxWidth(),
        content = content,
    )
}

