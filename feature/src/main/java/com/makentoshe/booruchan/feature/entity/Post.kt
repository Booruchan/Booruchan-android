package com.makentoshe.booruchan.feature.entity

data class Post(
    val id: String,
    /** Contains tag values */
    val tags: List<String>,
    val score: Int,

    val previewImageUrl: String,
    val previewImageWidth: Int,
    val previewImageHeight: Int,

    val sampleImageUrl: String,
    val sampleImageWidth: Int,
    val sampleImageHeight: Int,
)