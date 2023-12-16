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

internal fun LazyListScope.downloadImageItem() = item(key = "Download Image") {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallText(text = "Requires file storage permission", color = BooruchanTheme.colors.foreground)
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            PrimaryText(text = "TODO: DOWNLOAD IMAGE", color = BooruchanTheme.colors.dimmed)
        }
    }
}
