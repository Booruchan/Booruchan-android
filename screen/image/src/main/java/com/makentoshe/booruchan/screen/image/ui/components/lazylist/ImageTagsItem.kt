package com.makentoshe.booruchan.screen.image.ui.components.lazylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.makentoshe.booruchan.library.resources.R
import com.makentoshe.booruchan.screen.image.entity.SamplePostTagsState
import com.makentoshe.booruchan.screen.image.ui.components.chip.TagChipItem
import com.makentoshe.library.uikit.foundation.ChipGroup
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

internal fun LazyListScope.imageTagsItem(state: SamplePostTagsState) = item(key = "Tags") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(
            text = stringResource(id = R.string.component_tags_title),
            color = BooruchanTheme.colors.foreground,
        )
        ChipGroup(modifier = Modifier.fillMaxWidth()) {
            state.states.forEach { tagUiState ->
                TagChipItem(state = tagUiState)
            }
        }
    }
}
