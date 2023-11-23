package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity

@Entity(
    primaryKeys = ["source", "search", "timestamp"]
)
data class DatabaseActionSearchHistory(
    val source: String,
    val search: String,
    val timestamp: String,
)