package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.library.database.entity.DatabaseTag
import javax.inject.Inject

class Autocomplete2DatabaseTagMapper @Inject constructor() {
    fun map(sourceTitle: String, autocomplete: Autocomplete) = DatabaseTag(
        sourceTitle = sourceTitle,
        tagTitle = autocomplete.title,
        value = autocomplete.value,
        count = autocomplete.count,
    )
}
