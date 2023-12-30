package com.makentoshe.booruchan.screen.image.ui.components.lazylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonRow
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonState

internal fun LazyListScope.imageRatingItem(
    state: RatingSegmentedButtonState,
) = item(key = "Rating") {
    Column(modifier = Modifier.fillMaxWidth()) {
        RatingSegmentedButtonRow(state = state, enabled = false, onClick = { _, _ ->

        })
    }
}
