package com.makentoshe.booruchan.feature.entity

data class Autocomplete(
    /** Can be empty cause some sources may not provide it */
    val id: String,
    val title: String,
    val value: String,
    val count: Int,
    val type: TagType,
)