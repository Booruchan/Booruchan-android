package com.makentoshe.booruchan.feature.entity

data class Tag(
    /** Can be empty cause some sources may not return it for some requests */
    val id: String,
    val value: String,
    val type: TagType,
)
