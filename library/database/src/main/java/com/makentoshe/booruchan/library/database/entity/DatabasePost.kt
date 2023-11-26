package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id", "source"])
data class DatabasePost(
    /** Title of the source (gelbooru, google, pixiv) */
    val source: String,
    /** Id in the source */
    val id: String,

    val previewImageUrl: String,
    val previewImageWidth: Int,
    val previewImageHeight: Int,

    val sampleImageUrl: String,
    val sampleImageWidth: Int,
    val sampleImageHeight: Int,
)
