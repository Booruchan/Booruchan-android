package com.makentoshe.booruchan.screen.drawer.viewmodel

import com.makentoshe.booruchan.feature.entity.ActionSearchHistory

data class DrawerScreenState(
    val snapshots: List<ActionSearchHistory>,
) {
    companion object {
        val InitialState = DrawerScreenState(snapshots = emptyList())
    }
}