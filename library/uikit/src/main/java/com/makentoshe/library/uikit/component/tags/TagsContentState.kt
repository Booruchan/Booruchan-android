package com.makentoshe.library.uikit.component.tags

import androidx.compose.runtime.Immutable
import com.makentoshe.library.uikit.entity.TagUiState

@Immutable
data class TagsContentState(
    /** Is component should be visible */
    val visible: Boolean,
    /** All general tags that was added to the filter */
    val tags: Set<TagUiState>,
)
