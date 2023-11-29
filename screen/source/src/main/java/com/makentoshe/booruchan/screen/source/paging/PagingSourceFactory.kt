package com.makentoshe.booruchan.screen.source.paging

import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.factory.FetchPostsFactory
import com.makentoshe.booruchan.feature.usecase.FetchPostsUseCase
import com.makentoshe.booruchan.feature.usecase.SetPostsUseCase
import com.makentoshe.booruchan.screen.source.mapper.Post2PreviewPostUiStateMapper
import javax.inject.Inject

class PagingSourceFactory @Inject constructor(
    private val fetchPosts: FetchPostsUseCase,
    private val setPosts: SetPostsUseCase,
    private val mapper: Post2PreviewPostUiStateMapper,
) {
    fun buildPostPagingSource(source: Source, factory: FetchPostsFactory, query: String): PostPagingSource {
        return PostPagingSource(fetchPosts = fetchPosts, setPosts = setPosts, source = source, fetchPostsFactory = factory, mapper = mapper, query = query)
    }

    fun buildErrorPagingSource(throwable: Throwable): ErrorPagingSource {
        return ErrorPagingSource(throwable)
    }
}
