package com.makentoshe.booruchan.screen.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.makentoshe.booruchan.screen.drawer.viewmodel.DrawerScreenEvent
import com.makentoshe.booruchan.screen.drawer.viewmodel.DrawerScreenState

@Composable
fun DrawerScreenUi(
    screenState: DrawerScreenState,
    screenEvent: (DrawerScreenEvent) -> Unit,
) = Column {
    Text(screenState.snapshots.toString(), color = Color.Magenta)
}