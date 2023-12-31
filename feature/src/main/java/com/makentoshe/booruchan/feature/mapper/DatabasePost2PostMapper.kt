package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.library.database.entity.DatabasePost
import javax.inject.Inject

class DatabasePost2PostMapper @Inject constructor() {

    fun map(databasePost: DatabasePost, tags: List<String>) = Post(
        id = databasePost.id,
        tags = tags,
        score = databasePost.score,
        previewImageUrl = databasePost.previewImageUrl,
        previewImageHeight = databasePost.previewImageHeight,
        previewImageWidth = databasePost.previewImageWidth,
        sampleImageUrl = databasePost.sampleImageUrl,
        sampleImageHeight = databasePost.sampleImageHeight,
        sampleImageWidth = databasePost.sampleImageWidth,
    )
}
