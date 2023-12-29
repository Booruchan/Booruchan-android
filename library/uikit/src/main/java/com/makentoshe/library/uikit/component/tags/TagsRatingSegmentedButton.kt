package com.makentoshe.library.uikit.component.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.MultiChoiceSegmentedButtonRowScope
import androidx.compose.material3.SegmentedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.library.uikit.R
import com.makentoshe.library.uikit.foundation.SecondaryText
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun TagsRatingSegmentedButtonRow(
    state: TagsRatingSegmentedButtonState,
    enabled: Boolean = true,
    onClick: (Int, Boolean) -> Unit,
) = Column {

    SmallText(
        text = stringResource(id = R.string.source_search_tag_rating),
        color = BooruchanTheme.colors.foreground,
    )

    Spacer(modifier = Modifier.size(8.dp))

    MultiChoiceSegmentedButtonRow {
        state.values.forEachIndexed { index, value ->
            var shape = RoundedCornerShape(0.dp)

            if (index == 0) {
                shape = shape.copy(topStart = CornerSize(8.dp), bottomStart = CornerSize(8.dp))
            }

            if (state.values.lastIndex == index) {
                shape = shape.copy(topEnd = CornerSize(8.dp), bottomEnd = CornerSize(8.dp))
            }

            TagsRatingSegmentedButton(
                value = value,
                onClick = { onClick(index, it) },
                shape = shape,
                enabled = enabled,
                checked = state.selected.contains(index),
            )
        }
    }
}

@Composable
fun MultiChoiceSegmentedButtonRowScope.TagsRatingSegmentedButton(
    value: String,
    onClick: (Boolean) -> Unit,
    checked: Boolean = false,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
) = SegmentedButton(
    checked = checked,
    onCheckedChange = onClick,
    shape = shape,
    enabled = enabled,
    icon = {},
    label = { SecondaryText(value) },
)
