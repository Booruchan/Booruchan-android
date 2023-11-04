package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.SearchSnapshot
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot
import javax.inject.Inject

class DatabaseSearchSnapshot2SearchSnapshotMapper @Inject constructor() {
    fun map(searchSnapshot: DatabaseSearchSnapshot) = SearchSnapshot(
        source = searchSnapshot.source,
        tags = searchSnapshot.tags,
    )
}
