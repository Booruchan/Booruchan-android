package com.makentoshe.booruchan.feature

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.extension.base.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.extension.base.factory.HealthCheckFactory
import com.makentoshe.booruchan.extension.base.settings.SourceSettings

class SourceWrapper(private val source: Source) : Source {
    override val host: String
        get() = source.host

    override val id: String
        get() = source.id

    override val settings: SourceSettings
        get() = source.settings

    override val title: String
        get() = source.title

    override val healthCheckFactory: HealthCheckFactory?
        get() = tryOrNull { source.healthCheckFactory }

    override val fetchPostsFactory: FetchPostsFactory?
        get() = tryOrNull { source.fetchPostsFactory }

    override val autocompleteSearchFactory: AutocompleteSearchFactory?
        get() = tryOrNull { source.autocompleteSearchFactory }

    /** If source doesn't implemented method this method allows to avoid AME error */
    private fun <T> tryOrNull(action: () -> T?): T? = try {
        action()
    } catch (error: AbstractMethodError) {
        null
    }
}