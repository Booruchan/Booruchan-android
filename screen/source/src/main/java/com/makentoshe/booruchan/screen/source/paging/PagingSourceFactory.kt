package com.makentoshe.booruchan.screen.source.paging

import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.feature.fetchposts.FetchPostsUseCase
import com.makentoshe.booruchan.screen.source.mapper.Post2PreviewPostUiStateMapper
import javax.inject.Inject

class PagingSourceFactory @Inject constructor(
    private val fetchPosts: FetchPostsUseCase,
    private val mapper: Post2PreviewPostUiStateMapper,
) {
    fun buildPost(factory: FetchPostsFactory, query: String): PostPagingSource {
        return PostPagingSource(fetchPosts = fetchPosts, fetchPostsFactory = factory, mapper = mapper, query = query)
    }

    fun buildError(throwable: Throwable): ErrorPagingSource {
        return ErrorPagingSource(throwable)
    }
}
