package post.network

import network.GelbooruRequest

sealed class GelbooruPostRequest: GelbooruRequest(), PostRequest {
    protected val internalUrl = "$host/index.php?page=dapi&s=post&q=index"
}

data class XmlGelbooruPostRequest(private val filter: GelbooruPostFilter) : GelbooruPostRequest() {
    override val url = when (filter) {
        is GelbooruPostFilter.ById -> "$internalUrl&id=${filter.postId.postId}"
    }
}

data class JsonGelbooruPostRequest(private val filter: GelbooruPostFilter) : GelbooruPostRequest() {
    override val url = when (filter) {
        is GelbooruPostFilter.ById -> "$internalUrl&id=${filter.postId.postId}&json=1"
    }
}
