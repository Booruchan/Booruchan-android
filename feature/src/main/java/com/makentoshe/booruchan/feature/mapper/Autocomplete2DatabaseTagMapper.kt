package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.library.database.entity.DatabaseTag
import javax.inject.Inject

class Autocomplete2DatabaseTagMapper @Inject constructor() {
    fun map(sourceTitle: String, autocomplete: Autocomplete) = DatabaseTag(
        source = sourceTitle,
        title = autocomplete.title,
        value = autocomplete.value,
        count = autocomplete.count,
    )
}
