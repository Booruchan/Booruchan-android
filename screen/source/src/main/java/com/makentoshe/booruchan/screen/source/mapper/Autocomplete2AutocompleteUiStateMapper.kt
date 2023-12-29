package com.makentoshe.booruchan.screen.source.mapper

import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.screen.source.entity.AutocompleteUiState
import javax.inject.Inject

class Autocomplete2AutocompleteUiStateMapper @Inject constructor(
    private val tagType2TagTypeUiStateMapper: TagType2TagTypeUiStateMapper,
) {
    fun map(autocomplete: Autocomplete) = AutocompleteUiState(
        value = autocomplete.value,
        title = autocomplete.title,
        count = autocomplete.count.takeIf { it > 0 }?.toString() ?: "",
        type = tagType2TagTypeUiStateMapper.map(autocomplete.type),
    )
}