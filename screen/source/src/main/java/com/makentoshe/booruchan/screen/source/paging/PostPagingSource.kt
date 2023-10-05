package com.makentoshe.booruchan.screen.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.feature.fetchposts.FetchPostsUseCase
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.mapper.Post2PreviewPostUiStateMapper
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val fetchPosts: FetchPostsUseCase,
    private val fetchPostsFactory: FetchPostsFactory,
    private val mapper: Post2PreviewPostUiStateMapper,
    private val query: String,
) : PagingSource<Int, PreviewPostUiState>() {

    override fun getRefreshKey(state: PagingState<Int, PreviewPostUiState>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PreviewPostUiState> {
        return try {
            internalLoad(params)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }

    private suspend fun internalLoad(params: LoadParams<Int>): LoadResult<Int, PreviewPostUiState> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: fetchPostsFactory.initialPageNumber
        val postsPerPage = params.loadSize

        val fetchPostsRequest = FetchPostsFactory.FetchPostsRequest(postsPerPage, nextPageNumber, query)
        val response = fetchPosts(fetchPostsFactory, fetchPostsRequest)

        return LoadResult.Page(
            data = response.map(mapper::map),
            prevKey = null, // Only paging forward.
            nextKey = nextPageNumber + 1
        )
    }
}
