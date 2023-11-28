package com.makentoshe.booruchan.screen.image.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonRow
import com.makentoshe.library.uikit.component.tags.TagsRatingSegmentedButtonState
import com.makentoshe.library.uikit.foundation.OutlinedButton
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.foundation.SmallText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun ImageScreenPageSampleInfo(
    contentState: ContentState.Content,
) = LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 12.dp),
    userScrollEnabled = false,
    verticalArrangement = Arrangement.spacedBy(12.dp),
) {
    downloadImageItem()
    commentsItem()
    scoresItem()
    ratingItem(state = contentState.ratingSegmentedButtonState)

    item(key = "tags") {
        Text(text = "Tags")
    }

    item(key = "posted") {
        Text(text = "Posted")
    }
}

private fun LazyListScope.downloadImageItem() = item(key = "Download Image") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(text = "Requires file storage permission")
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(text = "TODO: DOWNLOAD IMAGE", color = BooruchanTheme.colors.dimmed)
        }
    }
}

private fun LazyListScope.commentsItem() = item(key = "Comments") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(text = "Comments: 4")
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(text = "TODO: OPEN COMMENTS", color = BooruchanTheme.colors.dimmed)
        }
    }
}

private fun LazyListScope.scoresItem() = item(key = "Scores") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(text = "Scores: 35 ( ↑48 and ↓13 )")
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(text = "TODO: SCORES", color = BooruchanTheme.colors.dimmed)
        }
    }
}

private fun LazyListScope.ratingItem(
    state: TagsRatingSegmentedButtonState,
) = item(key = "Rating") {
    Column(modifier = Modifier.fillMaxWidth()) {
        TagsRatingSegmentedButtonRow(state = state, enabled = false, onClick = {})
    }
}
