package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.SearchSnapshot
import com.makentoshe.booruchan.feature.mapper.DatabaseSearchSnapshot2SearchSnapshotMapper
import com.makentoshe.booruchan.library.database.SearchSnapshotDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Live updates of current source id and search preferences from database */
class FlowSearchSnapshotUseCase @Inject constructor(
    private val searchSnapshotDatabase: SearchSnapshotDatabase,
    private val mapper: DatabaseSearchSnapshot2SearchSnapshotMapper,
) {
    suspend operator fun invoke(): Flow<List<SearchSnapshot>> {
        return searchSnapshotDatabase.sourceSearchSnapshotDao().flowAll().map { it.map { mapper.map(it) } }
    }
}