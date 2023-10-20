package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseTag(
    /** Title of the source (gelbooru, google, pixiv) */
    val sourceTitle: String,
    /** Title of the tag (sky, blue eyes, etc) */
    val tagTitle: String,
    /** Tag value (sky, blue_eyes, etc) */
    @PrimaryKey
    val value: String,
    /** How many posts with this tag in the source with [sourceTitle] */
    val count: Int,
)
