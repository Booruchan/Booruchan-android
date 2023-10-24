package com.makentoshe.booruchan.feature.usecase

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.feature.entity.Post
import com.makentoshe.booruchan.feature.mapper.Post2DatabasePostMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import com.makentoshe.booruchan.library.database.entity.DatabaseTagPostCrossRef
import javax.inject.Inject

class SetPostsUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val mapper: Post2DatabasePostMapper,
) {
    suspend operator fun invoke(source: Source, posts: List<Post>) {
        // For each Post create a DatabasePost instance
        posts.map { post -> post to mapper.map(source, post) }.forEach { (post, databasePost) ->
            // Store Post in the database
            applicationDatabase.postDao().insert(databasePost)
            // Store each Post's tag in the database
            post.tags.forEach { tag ->
                applicationDatabase.postTagCrossRefDao().insert(DatabaseTagPostCrossRef(post.id, source.id, tag))
            }
        }
    }
}