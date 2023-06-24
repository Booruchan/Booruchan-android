package com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.makentoshe.booruchan.feature.BooruContext
import com.makentoshe.booruchan.feature.boorupost.domain.usecase.FetchBooruPostsUseCase
import com.makentoshe.booruchan.screen.boorucontent.domain.BooruPreviewPostUi
import com.makentoshe.booruchan.screen.boorucontent.mapper.BooruPost2BooruPreviewPostUiMapper
import javax.inject.Inject

class BooruPostPagingSource @Inject constructor(
    private val fetchBooruPosts: FetchBooruPostsUseCase,
    private val mapper: BooruPost2BooruPreviewPostUiMapper,
    private val booruContext: BooruContext,
): PagingSource<Int, BooruPreviewPostUi>() {

    override fun getRefreshKey(state: PagingState<Int, BooruPreviewPostUi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BooruPreviewPostUi> {
        return try {
            internalLoad(params)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }

    private suspend fun internalLoad(params: LoadParams<Int>): LoadResult<Int, BooruPreviewPostUi> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: booruContext.settings.searchSettings.initialPageNumber
        val postsPerPage = booruContext.settings.searchSettings.requestedPostsPerPageCount
        val tags = "hatsune_miku"
        val params = FetchBooruPostsUseCase.FetchBooruParams(postsPerPage, nextPageNumber, tags)
        val listBooruPostUi = fetchBooruPosts(booruContext, params).map(mapper::map)

        return LoadResult.Page(
            data = listBooruPostUi,
            prevKey = null, // Only paging forward.
            nextKey = nextPageNumber + 1
        )
    }
}