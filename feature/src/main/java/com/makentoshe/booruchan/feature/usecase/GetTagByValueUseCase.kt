package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.feature.mapper.DatabasePost2PostMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

class GetTagByValueUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
) {
    suspend operator fun invoke(sourceId: String, tagValue: String) : Post? {
        val databaseTags = applicationDatabase.tagDao().getBySourceAndTagValue(sourceId, tagValue)

        println(databaseTags)

        return null
    }
}