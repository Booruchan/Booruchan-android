package com.makentoshe.library.uikit.component.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.entity.TagUiState

@Composable
fun TagsComponent(
    state: TagsComponentState,
    factory: @Composable (TagUiState) -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    // Show copyright tags only if there are any tags to display
    if (state.copyrightTagsContentState.visible) {
        TagsContentCopyright(tagsContentState = state.copyrightTagsContentState, factory = factory)
    }

    // Show character tags only if there are any tags to display
    if (state.characterTagsContentState.visible) {
        TagsContentCharacter(tagsContentState = state.characterTagsContentState, factory = factory)
    }

    // Show artist tags only if there are any tags to display
    if (state.artistTagsContentState.visible) {
        TagsContentArtist(tagsContentState = state.artistTagsContentState, factory = factory)
    }

    // Show metadata tags only if there are any tags to display
    if (state.metadataTagsContentState.visible) {
        TagsContentMetadata(tagsContentState = state.metadataTagsContentState, factory = factory)
    }

    // Show general tags only if there are any tags to display
    if (state.generalTagsContentState.visible) {
        TagsContentGeneral(tagsContentState = state.generalTagsContentState, factory = factory)
    }
}
