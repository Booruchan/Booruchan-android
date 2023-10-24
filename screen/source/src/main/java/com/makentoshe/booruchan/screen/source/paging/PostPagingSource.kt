package com.makentoshe.booruchan.screen.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.feature.usecase.FetchPostsUseCase
import com.makentoshe.booruchan.feature.usecase.SetPostsUseCase
import com.makentoshe.booruchan.screen.source.entity.PreviewPostUiState
import com.makentoshe.booruchan.screen.source.mapper.Post2PreviewPostUiStateMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    /** Fetching posts from network */
    private val fetchPosts: FetchPostsUseCase,
    /** Set posts to local storage */
    private val setPosts: SetPostsUseCase,
    private val source: Source,
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
        val fetchPostsResponse = fetchPosts(fetchPostsFactory, fetchPostsRequest)

        // store posts in the database
        withContext(Dispatchers.IO) { setPosts(source = source, posts = fetchPostsResponse) }

        // publish the response
        return LoadResult.Page(
            data = fetchPostsResponse.map(mapper::map),
            prevKey = null, // Only paging forward.
            nextKey = nextPageNumber + 1
        )
    }
}
