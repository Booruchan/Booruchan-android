package com.makentoshe.screen.boorulist.viewmodel

/** Destinations that can be triggered */
sealed interface BoorulistDestination {
    object NoneDestination: BoorulistDestination

    /**
     * We provides booru url as a single point of truth
     * because user can't add multiply boorus with the same url
     * */
    data class BoorucontentDestination(val booruContextUrl: String): BoorulistDestination
}