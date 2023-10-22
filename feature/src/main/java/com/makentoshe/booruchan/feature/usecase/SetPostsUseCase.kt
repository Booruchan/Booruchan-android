package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.feature.mapper.Post2DatabasePostMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

class SetPostsUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val mapper: Post2DatabasePostMapper,
) {
    suspend operator fun invoke(source: Source, posts: List<Post>) {
        posts.map { mapper.map(source, it) }.forEach { applicationDatabase.postDao().insert(it) }
    }
}