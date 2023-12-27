package com.makentoshe.booruchan.screen.image.entity

import com.makentoshe.booruchan.screen.entity.TagUiState

data class SamplePostTagsState(
    val states: List<TagUiState>,
) {

    /** List of tag values retrieved from the post entity */
    val values: List<String>
        get()  = states.map { it.tag }
}