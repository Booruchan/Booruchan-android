package com.makentoshe.booruchan.screen.drawer.viewmodel

import com.makentoshe.booruchan.feature.entity.SearchSnapshot

data class DrawerScreenState(
    val snapshots: List<SearchSnapshot>,
) {
    companion object {
        val InitialState = DrawerScreenState(snapshots = emptyList())
    }
}