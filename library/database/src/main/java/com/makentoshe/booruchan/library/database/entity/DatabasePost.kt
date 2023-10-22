package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["sourceId", "sourceTitle"])
data class DatabasePost(
    /** Title of the source (gelbooru, google, pixiv) */
    val sourceTitle: String,
    /** Id in the source */
    val sourceId: String,

    val previewImageUrl: String,
    val previewImageWidth: Int,
    val previewImageHeight: Int,
)
