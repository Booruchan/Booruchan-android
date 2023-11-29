package com.makentoshe.booruchan.feature

import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.factory.AutocompleteSearchFactory
import org.booruchan.extension.sdk.factory.FetchPostsFactory
import org.booruchan.extension.sdk.factory.HealthCheckFactory
import org.booruchan.extension.sdk.settings.SourceSettings

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