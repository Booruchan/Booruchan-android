package com.makentoshe.booruchan.feature.boorupost.domain.usecase

import com.makentoshe.booruchan.feature.BooruContext
import com.makentoshe.booruchan.feature.boorupost.domain.BoorupostsRepositories
import com.makentoshe.booruchan.feature.boorupost.domain.mapper.NetworkBooruPost2BooruPostMapper
import com.makentoshe.booruchan.feature.boorupost.domain.repository.request.BoorupostsRequest
import javax.inject.Inject

class FetchBooruPostsUseCase @Inject constructor(
    private val repositories: BoorupostsRepositories,
    private val mapper: NetworkBooruPost2BooruPostMapper
) {
    suspend operator fun invoke(context: BooruContext, params: FetchBooruParams) {
        val repository = repositories.list.firstOrNull { it.supportedBooruSystem == context.system }

        val request = BoorupostsRequest(context.host.url, params.count, params.page, params.tags)
        val response = repository?.getPosts(request)?.booruPosts?.map { mapper.map(it) }
        println(response)
    }

    data class FetchBooruParams(
        // How many posts we want to retrieve. There might be a hard limit for posts per request.
        val count: Int,
        // The page number for pagination
        val page: Int,
        // The tags to search for
        val tags: String,
    )
}