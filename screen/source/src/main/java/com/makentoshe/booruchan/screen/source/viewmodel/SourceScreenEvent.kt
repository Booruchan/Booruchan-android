package com.makentoshe.booruchan.screen.source.viewmodel

sealed interface SourceScreenEvent {

    data class Initialize(val sourceId: String) : SourceScreenEvent

    object NavigationBack : SourceScreenEvent

    object NavigationBackdrop : SourceScreenEvent

    /** Stores current search in navigation storage. We can return to this screen state lately */
    object StoreSourceSearch : SourceScreenEvent

    data class SearchValueChange(val value: String): SourceScreenEvent

    data class SearchTagAdd(val tag: String): SourceScreenEvent

    data class SearchTagRemove(val tag: String): SourceScreenEvent

    object SearchApplyFilters: SourceScreenEvent
}