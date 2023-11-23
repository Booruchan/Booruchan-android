package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.feature.mapper.DatabasePost2PostMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val mapper: DatabasePost2PostMapper,
) {
    suspend operator fun invoke(sourceId: String, postId: String) : Post? {
        val databasePost = applicationDatabase.postDao().getBySourceAndId(sourceId, postId) ?: return null
        val databaseTags = applicationDatabase.postTagCrossRefDao().getBySourceAndPostId(sourceId, postId).map { it.value }
        return mapper.map(databasePost, databaseTags)
    }
}
