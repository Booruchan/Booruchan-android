package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.ActionSearchHistory
import com.makentoshe.booruchan.library.database.entity.DatabaseActionSearchHistory
import javax.inject.Inject

class DatabaseActionSearchHistory2SearchSnapshotMapper @Inject constructor() {
    fun map(searchSnapshot: DatabaseActionSearchHistory) = ActionSearchHistory(
        source = searchSnapshot.source,
        search = searchSnapshot.search,
        timestamp = searchSnapshot.timestamp,
    )
}
