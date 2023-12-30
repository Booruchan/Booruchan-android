package com.makentoshe.library.uikit.component.tags

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.makentoshe.booruchan.library.uikit.R
import com.makentoshe.library.uikit.entity.TagUiState
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun TagsContentCharacter(
    tagsContentState: TagsContentState,
    factory: @Composable (TagUiState) -> Unit,
) = if (tagsContentState.visible) {
    TagsWithRowLayout(
        label = {
            SmallText(
                text = stringResource(id = R.string.component_tags_character_title),
                color = BooruchanTheme.colors.tag.character,
            )
        },
        content = { tagsContentState.tags.forEach { tagUiState -> factory(tagUiState) } }
    )
} else Unit
