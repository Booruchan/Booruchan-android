package com.makentoshe.booruchan.screen.image.viewmodel

sealed interface ImageScreenEvent {

    data class Initialize(val sourceId: String, val postId: String): ImageScreenEvent

    object Retry: ImageScreenEvent
}