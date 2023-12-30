package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonState
import javax.inject.Inject

class Post2TagsRatingSegmentedButtonStateMapper @Inject constructor() {
    fun map(post: Post) = RatingSegmentedButtonState(
        values = listOf("Safe", "Questionable", "Explicit"),
        selected = emptyList()
    )
}