package com.makentoshe.library.uikit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.makentoshe.library.uikit.foundation.PrimaryTextBold
import com.makentoshe.library.uikit.foundation.SecondaryText

@Composable
fun ErrorComponent(
    state: ErrorComponentState,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    onClick: () -> Unit = {},
) = Column(
    modifier = modifier,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    PrimaryTextBold(text = state.title, textAlign = TextAlign.Start)
    SecondaryText(text = state.description, textAlign = TextAlign.Center)

    if (state.button != null)  {
        TextButton(onClick = onClick) {
            PrimaryTextBold(text = state.button)
        }
    }
}
