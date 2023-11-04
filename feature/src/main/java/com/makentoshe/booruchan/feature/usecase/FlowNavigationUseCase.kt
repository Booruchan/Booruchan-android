package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.library.database.SearchSnapshotDatabase
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Live updates of current source id and search preferences from database */
class FlowNavigationUseCase @Inject constructor(
    private val searchSnapshotDatabase: SearchSnapshotDatabase,
) {
    suspend operator fun invoke(): Flow<List<DatabaseSearchSnapshot>> {
        return searchSnapshotDatabase.sourceSearchSnapshotDao().flowAll()
    }
}