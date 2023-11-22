package com.makentoshe.booruchan.library.navigation

private typealias PostId = String
private typealias SourceId = String

class SourceScreenNavigator(
    val back: () -> Unit,
    val navigateToImageScreen: (SourceId, PostId) -> Unit,
)