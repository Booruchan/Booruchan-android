package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.screen.entity.TagUiStateType
import com.makentoshe.booruchan.screen.entity.TagUiState
import com.makentoshe.booruchan.screen.image.entity.SamplePostTagsState
import javax.inject.Inject

class Post2SamplePostTagsStateMapper @Inject constructor() {
    fun map(post: Post) = SamplePostTagsState(
        states = post.tags.map { tag -> TagUiState(tag, TagUiStateType.General) }
    )
}