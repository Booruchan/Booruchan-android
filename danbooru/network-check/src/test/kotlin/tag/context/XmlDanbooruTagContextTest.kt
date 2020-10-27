package tag.context

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import tag.network.DanbooruTagFilter
import DanbooruTagNetworkManager
import tag.network.XmlDanbooruTagRequest
import tag.tagId

class XmlDanbooruTagContextTest : DanbooruTagContextTest() {

    override val context = XmlDanbooruTagContext { DanbooruTagNetworkManager(HttpClient()).getTag(it) }

    @Test
    fun `should request xml tag`() = runBlocking {

        val request = XmlDanbooruTagRequest(DanbooruTagFilter.ById(tagId(1598277)))
        logger.info { "Xml url request: ${request.url}" }
        assertEquals("https://danbooru.donmai.us/tags/1598277.xml", request.url)

        val result = context.get(request)
        logger.info { "Result: $result" }
        val successResult = context.get(request).getOrNull()!!

        assertEquals(1598277, successResult.tag.tagId)
    }

}
