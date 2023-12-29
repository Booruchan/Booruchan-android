package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(primaryKeys =["value", "source"])
data class DatabaseTag(
    /** Title of the source (gelbooru, google, pixiv) */
    val source: String,
    /** Tag value (sky, blue_eyes, etc) */
    val value: String,
    /** How many posts with this tag in the source with [source] */
    val count: Int,
    /** Title of the tag (sky, blue eyes, etc) */
    val title: String,
    /** Id of the tag. May not be provided for some requests, so it can be empty */
    val id: String,
)
