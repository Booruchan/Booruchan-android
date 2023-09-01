package com.makentoshe.booruchan.screen.source.viewmodel

sealed interface SourceScreenEvent {

    data class Initialize(val sourceId: String) : SourceScreenEvent

    object NavigationBack : SourceScreenEvent

    object NavigationBackdrop : SourceScreenEvent

    data class SearchValueChange(val value: String): SourceScreenEvent

}