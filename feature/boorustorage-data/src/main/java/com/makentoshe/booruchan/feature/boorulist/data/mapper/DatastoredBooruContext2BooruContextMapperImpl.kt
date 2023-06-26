package com.makentoshe.booruchan.feature.boorulist.data.mapper

import com.makentoshe.booruchan.feature.context.BooruContext
import com.makentoshe.booruchan.feature.context.BooruHost
import com.makentoshe.booruchan.feature.context.BooruSearchSettings
import com.makentoshe.booruchan.feature.context.BooruSettings
import com.makentoshe.booruchan.feature.context.BooruSystem
import com.makentoshe.booruchan.feature.boorulist.domain.DatastoredBooruContext
import com.makentoshe.booruchan.feature.boorulist.domain.mapper.DatastoredBooruContext2BooruContextMapper
import javax.inject.Inject

class DatastoredBooruContext2BooruContextMapperImpl @Inject constructor(

) : DatastoredBooruContext2BooruContextMapper {

    override fun map(storedBooruContext: DatastoredBooruContext) = BooruContext(
        title = storedBooruContext.title,
        system = defineBooruSystem(storedBooruContext.system),
        host = BooruHost(storedBooruContext.host),
        settings = BooruSettings(
            searchSettings = BooruSearchSettings(
                requestedPostsPerPageCount = storedBooruContext.settings.searchSettings.requestedPostsPerPageCount,
                initialPageNumber = storedBooruContext.settings.searchSettings.initialPageNumber,
                hint = storedBooruContext.settings.searchSettings.hint,
                tagSeparator = storedBooruContext.settings.searchSettings.tagSeparator,
            )
        )
    )

    private fun defineBooruSystem(string: String) = when (string) {
        BooruSystem.Gelbooru020System.name -> BooruSystem.Gelbooru020System
        BooruSystem.Gelbooru025System.name -> BooruSystem.Gelbooru025System
        else -> BooruSystem.UndefinedSystem(string)
    }
}
