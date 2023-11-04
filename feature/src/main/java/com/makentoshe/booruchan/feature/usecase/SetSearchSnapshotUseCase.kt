package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.SourceSearchSnapshot
import com.makentoshe.booruchan.library.database.SearchSnapshotDatabase
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot
import javax.inject.Inject

/** Stores current source id and search preferences for future restore */
class SetSearchSnapshotUseCase @Inject constructor(
    private val searchSnapshotDatabase: SearchSnapshotDatabase,
) {
    suspend operator fun invoke(sourceSearchNavigation: SourceSearchSnapshot) {
        val databaseNavigation = DatabaseSearchSnapshot(source = sourceSearchNavigation.source, tags = sourceSearchNavigation.tags)
        searchSnapshotDatabase.sourceSearchSnapshotDao().insert(databaseNavigation)
    }
}
