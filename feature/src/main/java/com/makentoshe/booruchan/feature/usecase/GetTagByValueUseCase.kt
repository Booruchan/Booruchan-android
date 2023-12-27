package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.Tag
import com.makentoshe.booruchan.feature.interactor.SourceId
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

// TODO: finish getting tags from database and network
//  for displaying in image screen with proper group name
//  like "Artist", "Meta", "Character" etc
class GetTagByValueUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
) {
    suspend operator fun invoke(sourceId: String, tagValue: String) : Tag? {
        val databaseTag = applicationDatabase.tagDao().getBySourceAndTagValue(sourceId, tagValue)
        println(databaseTag)
        return null
    }

    suspend operator fun invoke(tagValue: String) : List<Pair<SourceId, Tag>> {
        val databaseTags = applicationDatabase.tagDao().getByTagValue(tagValue)
        println(databaseTags)
        return emptyList()
    }
}