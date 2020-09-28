package tag.context

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import tag.network.DanbooruTagsFilter
import tag.network.DanbooruTagsNetworkManager
import tag.network.XmlDanbooruTagsRequest
import java.util.logging.Logger

class XmlDanbooruTagsContextTest {

    private val logger = Logger.getLogger(this.javaClass.simpleName)

    @get:Rule
    val globalTimeout: Timeout = Timeout.seconds(30)

    @Test
    fun `should request xml tags`() = runBlocking {
        val context = XmlDanbooruTagsContext { DanbooruTagsNetworkManager(HttpClient()).getTags(it) }

        val request = XmlDanbooruTagsRequest(DanbooruTagsFilter(count = 5))
        logger.info { "Xml url request: ${request.url}" }
        assertEquals("https://danbooru.donmai.us/tags.xml?limit=5", request.url)

        val result = context.get(request)
        logger.info { "Result: $result" }
        val successResult = context.get(request).getOrNull()!!

        assertEquals(5, successResult.deserializes.size)
    }
}
