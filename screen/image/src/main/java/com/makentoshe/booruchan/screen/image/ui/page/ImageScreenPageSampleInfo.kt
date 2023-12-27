package com.makentoshe.booruchan.screen.image.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makentoshe.booruchan.screen.image.ui.components.lazylist.openCommentsItem
import com.makentoshe.booruchan.screen.image.ui.components.lazylist.downloadImageItem
import com.makentoshe.booruchan.screen.image.ui.components.lazylist.imageRatingItem
import com.makentoshe.booruchan.screen.image.ui.components.lazylist.imageScoresItem
import com.makentoshe.booruchan.screen.image.ui.components.lazylist.imageTagsItem
import com.makentoshe.booruchan.screen.image.viewmodel.ContentState

@Composable
internal fun ImageScreenPageSampleInfo(
    contentState: ContentState.Content,
) = LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 12.dp),
    userScrollEnabled = true,
    verticalArrangement = Arrangement.spacedBy(12.dp),
) {
    downloadImageItem()
    openCommentsItem()
    imageScoresItem()
    imageRatingItem(state = contentState.samplePostRatingState)
    imageTagsItem(state = contentState.samplePostTagsState)

    item(key = "posted") {
        Box(modifier = Modifier.fillMaxWidth().height(256.dp).background(Color.Magenta)) {
            Text(text = "Posted")
        }
    }

    item(key = "test1") {
        Box(modifier = Modifier.fillMaxWidth().height(256.dp).background(Color.Blue)) {
            Text(text = "Test1")
        }
    }

    item(key = "test2") {
        Box(modifier = Modifier.fillMaxWidth().height(256.dp).background(Color.Green)) {
            Text(text = "Test2")
        }
    }
}
