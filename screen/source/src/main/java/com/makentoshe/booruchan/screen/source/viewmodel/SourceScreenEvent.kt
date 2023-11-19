package com.makentoshe.booruchan.screen.source.viewmodel

sealed interface SourceScreenEvent {

    data class Initialize(val sourceId: String) : SourceScreenEvent

    /** Navigates back from the current screen */
    object NavigationBack : SourceScreenEvent

    /** Shows error using snackbar */
    data class ShowSnackbar(val throwable: Throwable): SourceScreenEvent

    /** Dismiss any snackbar */
    object DismissSnackbar: SourceScreenEvent

    /** Shows a simple search view above the content */
    object ShowSearch: SourceScreenEvent

    /** Dismiss search view above the content */
    object DismissSearch: SourceScreenEvent

    /** Stores current search in navigation storage. We can return to this screen state lately */
    object StoreSourceSearch : SourceScreenEvent

    data class SearchValueChange(val value: String): SourceScreenEvent

    data class SearchTagAdd(val tag: String): SourceScreenEvent

    data class SearchTagRemove(val tag: String): SourceScreenEvent

    object SearchApplyFilters: SourceScreenEvent
}