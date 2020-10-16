package post.context

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import post.network.GelbooruPostFilter
import post.network.GelbooruPostNetworkManager
import post.network.XmlGelbooruPostRequest
import post.postId

class XmlGelbooruPostContextTest : GelbooruPostContextTest() {

    override val context = XmlGelbooruPostContext { GelbooruPostNetworkManager(HttpClient()).getPost(it) }

    @Test
    fun `should request xml post`() = runBlocking {
        val request = XmlGelbooruPostRequest(GelbooruPostFilter.ById(postId(5612477)))
        logger.info { "Xml url request: ${request.url}" }
        assertEquals("https://gelbooru.com/index.php?page=dapi&s=post&q=index&id=5612477", request.url)

        val result = context.get(request)
        logger.info { "Result: $result" }
        val successResult = context.get(request).getOrNull()!!

        assertEquals(5612477, successResult.post.postId)
    }

}
