package com.makentoshe.library.uikit.component.tags

data class TagsRatingSegmentedButtonState(
    val values: List<String>,
    val selected: List<Int> = emptyList(),
)