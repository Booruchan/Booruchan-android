package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id", "value"])
data class DatabaseTagDatabasePostCrossRef(
    val id: String,
    val value: String,
)