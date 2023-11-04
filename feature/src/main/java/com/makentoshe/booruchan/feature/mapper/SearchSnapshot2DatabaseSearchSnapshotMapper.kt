package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.SearchSnapshot
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot
import javax.inject.Inject

class SearchSnapshot2DatabaseSearchSnapshotMapper @Inject constructor() {
    fun map(searchSnapshot: SearchSnapshot) = DatabaseSearchSnapshot(
        source = searchSnapshot.source,
        tags = searchSnapshot.tags,
    )
}
