package post.context

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import post.network.GelbooruPostFilter
import post.network.GelbooruPostNetworkManager
import post.network.JsonGelbooruPostRequest
import post.postId

class JsonGelbooruPostContextTest: GelbooruPostContextTest() {

    override val context = JsonGelbooruPostContext { GelbooruPostNetworkManager(HttpClient()).getPost(it) }

    @Test
    fun `should request json post`() = runBlocking {
        val request = JsonGelbooruPostRequest(GelbooruPostFilter.ById(postId(1)))
        logger.info { "Json url request: ${request.url}" }
        assertEquals("https://gelbooru.com/index.php?page=dapi&s=post&q=index&id=1&json=1", request.url)

        val result = context.get(request)
        logger.info { "Result: $result" }
        val successResult = result.getOrNull()!!

        assertEquals(1, successResult.post.postId)
    }

}