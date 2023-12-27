package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.screen.image.entity.SamplePostImageState
import javax.inject.Inject

class Post2SamplePostImageStateMapper @Inject constructor() {
    fun map(post: Post) = SamplePostImageState(
        id = post.id,
        url = post.sampleImageUrl,
        ratio = post.sampleImageWidth.toFloat() / post.sampleImageHeight.toFloat(),
    )
}
