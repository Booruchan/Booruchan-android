package com.makentoshe.booruchan.feature.mapper

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.library.database.entity.DatabasePost
import javax.inject.Inject

class Post2DatabasePostMapper @Inject constructor() {

    fun map(source: Source, networkPost: Post) = DatabasePost(
        id = networkPost.id,
        source = source.id,
        previewImageUrl = networkPost.previewImageUrl,
        previewImageHeight = networkPost.previewImageHeight,
        previewImageWidth = networkPost.previewImageWidth,
        sampleImageUrl = networkPost.sampleImageUrl,
        sampleImageHeight = networkPost.sampleImageHeight,
        sampleImageWidth = networkPost.sampleImageWidth,
    )
}
