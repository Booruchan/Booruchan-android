package com.makentoshe.booruchan.screen.image.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.screen.image.entity.SamplePostScoreState
import javax.inject.Inject

class Post2SamplePostScoreStateMapper @Inject constructor() {
    fun map(post: Post) = SamplePostScoreState(
        score = post.score,
    )
}