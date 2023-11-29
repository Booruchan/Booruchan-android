package com.makentoshe.booruchan.feature.mapper

import org.booruchan.extension.sdk.entity.NetworkPost
import com.makentoshe.booruchan.feature.entity.Post
import javax.inject.Inject

class NetworkPost2PostMapper @Inject constructor() {
    fun map(networkPost: NetworkPost) = Post(
        id = networkPost.id.toString(),
        tags = networkPost.tags,
        previewImageUrl = networkPost.previewImageUrl,
        previewImageHeight = networkPost.previewImageHeight,
        previewImageWidth = networkPost.previewImageWidth,
        sampleImageUrl = networkPost.sampleImageUrl,
        sampleImageHeight = networkPost.sampleImageHeight,
        sampleImageWidth = networkPost.sampleImageWidth,
    )
}
