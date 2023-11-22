package com.makentoshe.booruchan.screen.image.viewmodel

data class ImageScreenState(
    val sourceId: String,
    val postId: String,
) {
    companion object {
        val InitialState = ImageScreenState(
            sourceId = "",
            postId = "",
        )
    }
}