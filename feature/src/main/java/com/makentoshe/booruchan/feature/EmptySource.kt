package com.makentoshe.booruchan.feature

import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.factory.AutocompleteSearchFactory
import org.booruchan.extension.sdk.factory.FetchPostsFactory
import org.booruchan.extension.sdk.factory.HealthCheckFactory
import org.booruchan.extension.sdk.settings.SourceSearchSettings
import org.booruchan.extension.sdk.settings.SourceSettings

object EmptySource : Source {
    override val host: String get() = "host"

    override val id: String get() = "id"

    override val title: String get() = ""

    override val settings: SourceSettings
        get() = SourceSettings(SourceSearchSettings())

    override val healthCheckFactory: HealthCheckFactory?
        get() = null

    override val fetchPostsFactory: FetchPostsFactory?
        get() = null

    override val autocompleteSearchFactory: AutocompleteSearchFactory?
        get() = null
}