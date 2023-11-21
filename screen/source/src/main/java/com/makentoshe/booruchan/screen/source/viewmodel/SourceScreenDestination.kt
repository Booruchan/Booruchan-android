package com.makentoshe.booruchan.screen.source.viewmodel

sealed interface SourceScreenDestination {
    object BackDestination : SourceScreenDestination
    data class ImageDestination(val postId: String, val sourceId: String) : SourceScreenDestination
}