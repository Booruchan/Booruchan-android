package com.makentoshe.booruchan.feature

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.extension.base.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.extension.base.factory.HealthCheckFactory
import com.makentoshe.booruchan.extension.base.settings.SourceSearchSettings
import com.makentoshe.booruchan.extension.base.settings.SourceSettings

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