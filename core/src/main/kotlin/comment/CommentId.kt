package comment

interface CommentId {
    val commentId: Int
}

fun commentId(id: Int) = object : CommentId {
    override val commentId = id
}