package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.Tag
import com.makentoshe.booruchan.feature.mapper.DatabaseTag2TagMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

class GetTagByValueUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val databaseTag2TagMapper: DatabaseTag2TagMapper,
) {
    suspend operator fun invoke(sourceId: String, tagValue: String): Tag? {
        val databaseTag = applicationDatabase.tagDao().getBySourceAndTagValue(sourceId, tagValue)
            ?: return null

        return databaseTag2TagMapper.map(databaseTag)
    }

    suspend operator fun invoke(tagValue: String): List<Tag> {
        val databaseTags = applicationDatabase.tagDao().getByTagValue(tagValue)
        return databaseTags.map { databaseTag2TagMapper.map(it) }
    }
}