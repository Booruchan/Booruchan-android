package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id", "source", "value"])
data class DatabaseTagPostCrossRef(
    /** Post id */
    val id: String,
    /** Source title */
    val source: String,
    /** Tag value */
    val value: String,
)