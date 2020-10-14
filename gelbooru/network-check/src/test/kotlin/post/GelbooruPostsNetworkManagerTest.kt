package post

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import post.deserialize.JsonGelbooruPostsDeserializer
import post.deserialize.XmlGelbooruPostsDeserializer
import post.network.GelbooruPostsFilter
import post.network.GelbooruPostsNetworkManager
import post.network.JsonGelbooruPostsRequest
import post.network.XmlGelbooruPostsRequest
import java.util.logging.Logger

class GelbooruPostsNetworkManagerTest {

    private val logger = Logger.getLogger(this.javaClass.simpleName)

    @get:Rule
    val globalTimeout: Timeout = Timeout.seconds(30)

    @Test
    fun `should request json posts`() = runBlocking {
        val request = JsonGelbooruPostsRequest(GelbooruPostsFilter(count = 10))
        logger.info { "Json url request: ${request.url}" }
        assertEquals("https://gelbooru.com/index.php?page=dapi&s=post&q=index&limit=10&json=1", request.url)
        val response = GelbooruPostsNetworkManager(HttpClient()).getPosts(request)
        logger.info { "Response: $response" }

        // deserialize json and check: was the filter condition satisfied?
        val deserializeResult = JsonGelbooruPostsDeserializer().deserializePosts(response.getOrNull()!!)
        val deserializeSuccess = deserializeResult.getOrNull()!!
        assertEquals(10, deserializeSuccess.deserializes.size)
        logger.info { "Success: ${deserializeSuccess.posts.size}" }
        logger.info { "Failure: ${deserializeSuccess.failures.size}" }
    }

    @Test
    fun `should request xml posts`() = runBlocking {
        val request = XmlGelbooruPostsRequest(GelbooruPostsFilter(count = 10))
        logger.info { "Xml url request: ${request.url}" }
        assertEquals("https://gelbooru.com/index.php?page=dapi&s=post&q=index&limit=10", request.url)
        val response = GelbooruPostsNetworkManager(HttpClient()).getPosts(request)
        logger.info { "Response: $response" }

        // deserialize xml and check: was the filter condition satisfied?
        val deserializeResult = XmlGelbooruPostsDeserializer().deserializePosts(response.getOrNull()!!)
        val deserializeSuccess = deserializeResult.getOrNull()!!
        assertEquals(10, deserializeSuccess.deserializes.size)
        logger.info { "Success: ${deserializeSuccess.posts.size}" }
        logger.info { "Failure: ${deserializeSuccess.failures.size}" }
    }
}