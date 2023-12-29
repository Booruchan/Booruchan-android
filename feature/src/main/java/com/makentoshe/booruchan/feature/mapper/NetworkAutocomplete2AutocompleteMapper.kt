package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.Autocomplete
import org.booruchan.extension.sdk.entity.NetworkAutocomplete
import javax.inject.Inject

class NetworkAutocomplete2AutocompleteMapper @Inject constructor(
    private val networkTagType2TagTypeMapper: NetworkTagType2TagTypeMapper,
) {
    fun map(networkAutocomplete: NetworkAutocomplete) = Autocomplete(
        title = networkAutocomplete.title,
        value = networkAutocomplete.value,
        count = networkAutocomplete.count,
        type = networkTagType2TagTypeMapper.map(networkAutocomplete.type),
        id = "",
    )
}
