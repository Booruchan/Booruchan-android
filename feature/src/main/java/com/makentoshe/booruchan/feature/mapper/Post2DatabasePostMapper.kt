package com.makentoshe.booruchan.feature.mapper

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.library.database.entity.DatabasePost
import javax.inject.Inject

class Post2DatabasePostMapper @Inject constructor() {

    fun map(source: Source, post: Post) = DatabasePost(
        id = post.id,
        source = source.id,
        score = post.score,
        previewImageUrl = post.previewImageUrl,
        previewImageHeight = post.previewImageHeight,
        previewImageWidth = post.previewImageWidth,
        sampleImageUrl = post.sampleImageUrl,
        sampleImageHeight = post.sampleImageHeight,
        sampleImageWidth = post.sampleImageWidth,
    )
}
