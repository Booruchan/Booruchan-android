package comment.context

import comment.commentId
import comment.network.DanbooruCommentFilter
import comment.network.DanbooruCommentNetworkManager
import comment.network.JsonDanbooruCommentRequest
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class JsonDanbooruCommentContextTest : DanbooruCommentContextTest() {

    override val context = JsonDanbooruCommentContext { DanbooruCommentNetworkManager(HttpClient()).getComment(it) }

    @Test
    fun `should request json comment`() = runBlocking {
        val request = JsonDanbooruCommentRequest(DanbooruCommentFilter.ById(commentId(1)))
        logger.info { "Json url request: ${request.url}" }
        assertEquals("https://danbooru.donmai.us/comments/1.json", request.url)

        val result = context.get(request)
        logger.info { "Result: $result" }
        val successResult = result.getOrNull()!!

        assertEquals(1, successResult.comment.commentId)
    }

}