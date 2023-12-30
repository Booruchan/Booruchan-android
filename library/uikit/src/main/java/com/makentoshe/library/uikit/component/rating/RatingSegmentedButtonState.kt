package com.makentoshe.library.uikit.component.rating

data class RatingSegmentedButtonState(
    val values: List<String>,
    val selected: List<Int> = emptyList(),
)