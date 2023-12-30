package com.makentoshe.booruchan.screen.source.mapper

import com.makentoshe.booruchan.feature.entity.Tag
import com.makentoshe.library.uikit.entity.TagUiState
import javax.inject.Inject

class Tag2TagUiStateMapper @Inject constructor(
    private val tagType2TagTypeUiStateMapper: TagType2TagTypeUiStateMapper,
) {
    fun map(tag: Tag) = TagUiState(
        tag = tag.value,
        type = tagType2TagTypeUiStateMapper.map(tag.type),
    )
}