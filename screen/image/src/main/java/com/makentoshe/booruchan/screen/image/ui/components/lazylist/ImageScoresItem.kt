package com.makentoshe.booruchan.screen.image.ui.components.lazylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.makentoshe.booruchan.screen.image.R
import com.makentoshe.booruchan.screen.image.entity.SamplePostScoreState
import com.makentoshe.library.uikit.foundation.OutlinedButton
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

internal fun LazyListScope.imageScoresItem(state: SamplePostScoreState) = item(key = "Scores") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(
            text = stringResource(id = R.string.image_scores_title, state.score),
            color = BooruchanTheme.colors.foreground,
        )

        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(
                text = stringResource(id = R.string.image_scores_button_todo),
                color = BooruchanTheme.colors.dimmed,
            )
        }
    }
}
