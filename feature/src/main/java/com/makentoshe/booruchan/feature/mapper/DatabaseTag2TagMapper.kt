package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.Tag
import com.makentoshe.booruchan.feature.entity.TagType
import com.makentoshe.booruchan.library.database.entity.DatabaseTag
import javax.inject.Inject

class DatabaseTag2TagMapper @Inject constructor() {
    fun map(databaseTag: DatabaseTag) = Tag(
        id = databaseTag.id,
        value = databaseTag.value,
        type = TagType.valueOf(databaseTag.type),
    )
}