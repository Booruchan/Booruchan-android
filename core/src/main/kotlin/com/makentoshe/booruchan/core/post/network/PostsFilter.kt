package com.makentoshe.booruchan.core.post.network

import com.makentoshe.booruchan.core.network.Filter
import com.makentoshe.booruchan.core.post.Tags
import com.makentoshe.booruchan.core.post.string

abstract class PostsFilter(protected val params: Map<String, Any>) : Filter {

    /**
     * First character of the url.
     *
     * It can be "&" if query should be continued or "?" if should be started.
     */
    protected abstract val firstChar: String

    override fun toUrl(): String {
        if (params.isEmpty()) return ""
        return params.entries.mapIndexed { index, entry ->
            "${if (index == 0) firstChar else "&"}${entry.key}=${entry.value}"
        }.joinToString("")
    }

    abstract class Builder {

        abstract val countKey: String
        abstract val pageKey: String
        abstract val tagsKey: String

        abstract fun build(count: Int? = null, page: Int? = null, tags: Tags = Tags.EMPTY): PostsFilter

        protected fun params(count: Int?, page: Int?, tags: Tags) = HashMap<String, Any>().apply {
            if (count != null) this[countKey] = count
            if (page != null) this[pageKey] = page
            if (tags.tags.isNotEmpty()) this[tagsKey] = tags.string
        }
    }
}