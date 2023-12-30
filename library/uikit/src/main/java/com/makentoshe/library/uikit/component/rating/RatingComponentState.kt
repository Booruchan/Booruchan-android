package com.makentoshe.library.uikit.component.rating

data class RatingComponentState(
    /** Is component should be visible */
    val visible: Boolean,
    /** All values for "rating" metadata tag */
    val ratingTagSegmentedButtonState: RatingSegmentedButtonState,
)
