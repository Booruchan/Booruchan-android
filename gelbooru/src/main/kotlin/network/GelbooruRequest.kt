package network

abstract class GelbooruRequest {

    val host: String = "https://gelbooru.com"

    abstract val url: String
}
