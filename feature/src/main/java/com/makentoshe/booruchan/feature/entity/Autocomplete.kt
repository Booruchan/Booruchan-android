package com.makentoshe.booruchan.feature.entity

data class Autocomplete(
    val title: String,
    val value: String,
    val count: Int,
    val type: TagType,
)