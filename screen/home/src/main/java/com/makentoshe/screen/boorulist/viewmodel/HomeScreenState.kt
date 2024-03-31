package com.makentoshe.screen.boorulist.viewmodel

import androidx.compose.runtime.Immutable
import com.makentoshe.screen.boorulist.entity.SourceUiState

@Immutable
data class HomeScreenState(
    val floatingButtonText: String,
    val sourcePageState: HomeScreenSourcePageState,
) {
    companion object {
        val InitialState = HomeScreenState(
            floatingButtonText = "Add Plugins",
            sourcePageState = HomeScreenSourcePageState(
                title = "",
                content = HomeScreenSourcePageContent.None,
            ),
        )
    }
}

@Immutable
data class HomeScreenSourcePageState(
    val title: String,
    val content: HomeScreenSourcePageContent,
)

sealed interface HomeScreenSourcePageContent {

    object None: HomeScreenSourcePageContent

    object Loading : HomeScreenSourcePageContent

    data class Content(
        val sources: List<SourceUiState>,
        val refreshing: Boolean,
    ) : HomeScreenSourcePageContent
}

val HomeScreenState.tabs: List<String>
    get() = listOf(sourcePageState.title)