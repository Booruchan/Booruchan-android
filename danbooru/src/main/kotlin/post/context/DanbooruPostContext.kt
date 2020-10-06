package post.context

import post.deserialize.DanbooruPostDeserialize
import post.deserialize.JsonDanbooruPostDeserializer
import post.deserialize.XmlDanbooruPostDeserializer
import post.network.DanbooruPostFilter
import post.network.DanbooruPostRequest
import post.network.JsonDanbooruPostRequest
import post.network.XmlDanbooruPostRequest

abstract class DanbooruPostContext<Request : DanbooruPostRequest>(
    network: suspend (Request) -> Result<String>,
    deserialize: (String) -> Result<DanbooruPostDeserialize<*>>
) : PostContext<Request, DanbooruPostFilter>(network, deserialize)

open class JsonDanbooruPostContext(
    network: suspend (JsonDanbooruPostRequest) -> Result<String>
) : DanbooruPostContext<JsonDanbooruPostRequest>(
    network, { json -> JsonDanbooruPostDeserializer().deserializePost(json) }
) {
    override fun buildRequest(filter: DanbooruPostFilter) = JsonDanbooruPostRequest(filter)
}

open class XmlDanbooruPostContext(
    network: suspend (XmlDanbooruPostRequest) -> Result<String>
) : DanbooruPostContext<XmlDanbooruPostRequest>(
    network, { xml -> XmlDanbooruPostDeserializer().deserializePost(xml) }
) {
    override fun buildRequest(filter: DanbooruPostFilter) = XmlDanbooruPostRequest(filter)
}