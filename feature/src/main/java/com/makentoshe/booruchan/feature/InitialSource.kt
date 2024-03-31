package com.makentoshe.booruchan.feature

import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.factory.AutocompleteSearchFactory
import org.booruchan.extension.sdk.factory.FetchPostsFactory
import org.booruchan.extension.sdk.factory.HealthCheckFactory
import org.booruchan.extension.sdk.settings.SourceSearchSettings
import org.booruchan.extension.sdk.settings.SourceSettings

/** Default initial source. This source indicates that source was not declared */
object InitialSource : Source {
    override val host: String get() = ""

    override val id: String get() = "initial-source"

    override val title: String get() = "Initial source"

    override val settings: SourceSettings
        get() = SourceSettings(SourceSearchSettings())

    override val healthCheckFactory: HealthCheckFactory?
        get() = null

    override val fetchPostsFactory: FetchPostsFactory?
        get() = null

    override val autocompleteSearchFactory: AutocompleteSearchFactory?
        get() = null
}