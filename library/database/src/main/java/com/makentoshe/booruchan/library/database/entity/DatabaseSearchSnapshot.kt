package com.makentoshe.booruchan.library.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores whole info about source screen: source id and
 * search preferences for future restoration
 * */
@Entity(primaryKeys = ["source", "tags"])
data class DatabaseSearchSnapshot(
    val source: String,
    val tags: String,
)
