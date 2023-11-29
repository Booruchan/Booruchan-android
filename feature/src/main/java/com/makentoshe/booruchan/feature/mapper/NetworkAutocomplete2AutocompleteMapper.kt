package com.makentoshe.booruchan.feature.mapper

import org.booruchan.extension.sdk.entity.NetworkAutocomplete
import com.makentoshe.booruchan.feature.entity.Autocomplete
import javax.inject.Inject

class NetworkAutocomplete2AutocompleteMapper @Inject constructor() {
    fun map(networkAutocomplete: NetworkAutocomplete) = Autocomplete(
        title = networkAutocomplete.title,
        value = networkAutocomplete.value,
        count = networkAutocomplete.count,
    )
}
