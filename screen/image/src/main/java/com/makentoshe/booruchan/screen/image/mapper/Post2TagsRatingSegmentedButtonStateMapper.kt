package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonState
import javax.inject.Inject

class Post2TagsRatingSegmentedButtonStateMapper @Inject constructor() {
    fun map(post: Post) = TagsRatingSegmentedButtonState(
        values = listOf("Safe", "Questionable", "Explicit"),
        selected = 0,
    )
}