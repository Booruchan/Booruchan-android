package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.library.database.entity.DatabaseTag
import javax.inject.Inject

class Autocomplete2DatabaseTagMapper @Inject constructor() {
    fun map(source: Source, autocomplete: Autocomplete) = DatabaseTag(
        source = source.id,
        title = autocomplete.title,
        value = autocomplete.value,
        count = autocomplete.count,
    )
}
