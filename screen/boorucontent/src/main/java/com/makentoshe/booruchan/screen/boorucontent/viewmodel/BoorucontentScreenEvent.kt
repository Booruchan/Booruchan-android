package com.makentoshe.booruchan.screen.boorucontent.viewmodel

sealed interface BoorucontentScreenEvent {
    data class Initialize(val booruContextUrl: String): BoorucontentScreenEvent

    data class Search(val searchQuery: String): BoorucontentScreenEvent

    data class Autocomplete(val autocompleteQuery: String): BoorucontentScreenEvent

    object NavigationBack: BoorucontentScreenEvent
}