package com.makentoshe.booruchan.screen.source.viewmodel

sealed interface SourceScreenEvent {

    data class Initialize(val sourceId: String) : SourceScreenEvent

    /** Navigates back from the current screen */
    object NavigationBack : SourceScreenEvent

    /** Navigates forward to the fullscreen image screen */
    data class NavigationImage(val id: String) : SourceScreenEvent

    /** Shows error using snackbar */
    data class ShowSnackbar(val throwable: Throwable) : SourceScreenEvent

    /** Dismiss any snackbar */
    object DismissSnackbar : SourceScreenEvent

    /** Shows a simple search view above the content */
    object ShowSearch : SourceScreenEvent

    /** Dismiss search view above the content */
    object DismissSearch : SourceScreenEvent

    /** Invokes on each symbol input from the keyboard */
    data class SearchValueChange(val value: String) : SourceScreenEvent

    data class SuggestedItemClicked(val value: String) : SourceScreenEvent

    data class SearchTagAdd(val tag: String) : SourceScreenEvent

    data class SearchTagRemove(val tag: String) : SourceScreenEvent

    /** Add rating meta tag by its index */
    data class SearchTagChangeRating(val index: Int) : SourceScreenEvent

    object SearchApplyFilters : SourceScreenEvent
}