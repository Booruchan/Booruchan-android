package com.makentoshe.booruchan.screen.source.entity

import com.makentoshe.booruchan.screen.entity.TagTypeUiState

data class AutocompleteUiState(
    val value: String,
    val title: String,
    val count: String,
    val type: TagTypeUiState,
)