package com.makentoshe.booruchan.screen.source.mapper

import com.makentoshe.booruchan.feature.entity.TagType
import com.makentoshe.booruchan.screen.entity.TagTypeUiState
import javax.inject.Inject

class TagType2TagTypeUiStateMapper @Inject constructor() {
    fun map(tagType: TagType) = when (tagType) {
        TagType.General -> TagTypeUiState.General
        TagType.Artist -> TagTypeUiState.Artist
        TagType.Character -> TagTypeUiState.Character
        TagType.Copyright -> TagTypeUiState.Copyright
        TagType.Metadata -> TagTypeUiState.Metadata
        TagType.Other -> TagTypeUiState.Other
    }
}