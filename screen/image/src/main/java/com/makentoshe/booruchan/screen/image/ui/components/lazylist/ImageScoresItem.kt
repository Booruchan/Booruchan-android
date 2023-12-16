package com.makentoshe.booruchan.screen.image.ui.components.lazylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonRow
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonState
import com.makentoshe.library.uikit.foundation.OutlinedButton
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

internal fun LazyListScope.imageScoresItem() = item(key = "Scores") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(text = "Scores: 35 ( ↑48 and ↓13 )", color = BooruchanTheme.colors.foreground)
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(text = "TODO: SCORES", color = BooruchanTheme.colors.dimmed)
        }
    }
}
