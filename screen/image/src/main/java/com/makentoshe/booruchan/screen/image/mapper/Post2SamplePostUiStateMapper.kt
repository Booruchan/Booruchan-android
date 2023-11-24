package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.screen.image.entity.SamplePostUiState
import javax.inject.Inject

class Post2SamplePostUiStateMapper @Inject constructor() {
    fun map(post: Post) = SamplePostUiState(
        id = post.id,
        url = post.previewImageUrl,
    )
}