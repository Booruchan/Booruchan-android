@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.makentoshe.booruchan.screen.source.ui.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import com.makentoshe.booruchan.screen.source.viewmodel.SearchState
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenEvent
import com.makentoshe.booruchan.screen.source.viewmodel.SourceScreenState

@Composable
internal fun SourceScreenSearch(
    screenState: SourceScreenState,
    screenEvent: (SourceScreenEvent) -> Unit,
) = AnimatedContent(
    targetState = screenState.searchState.fullScreenState,
    label = "label",
    transitionSpec = { sourceScreenSearchTransition() },
    content = {
        BackHandler(enabled = it is SearchState.FullScreenState.Expanded) {
            screenEvent(SourceScreenEvent.DismissSearch)
        }

        if (it is SearchState.FullScreenState.Expanded) {
            SourceScreenSearchContent(screenState = screenState, screenEvent = screenEvent)
        }
    }
)

private fun AnimatedContentScope<SearchState.FullScreenState>.sourceScreenSearchTransition(): ContentTransform {
    return sourceScreenSearchFadeIn() with sourceScreenSearchFadeOut() using sourceScreenSearchSizeTransform()
}

private fun AnimatedContentScope<SearchState.FullScreenState>.sourceScreenSearchSizeTransform(): SizeTransform {
    return SizeTransform { initialSize, targetSize ->
        if (targetState is SearchState.FullScreenState.Expanded) {
            keyframes {
                // Expand horizontally first.
                IntSize(targetSize.width, initialSize.height) at 150
                durationMillis = 300
            }
        } else {
            keyframes {
                // Shrink vertically first.
                IntSize(initialSize.width, targetSize.height) at 150
                durationMillis = 300
            }
        }
    }
}

private fun sourceScreenSearchFadeIn() = fadeIn(
    animationSpec = tween(durationMillis = 150, delayMillis = 150),
)

private fun sourceScreenSearchFadeOut() = fadeOut(
    animationSpec = tween(durationMillis = 150),
)
