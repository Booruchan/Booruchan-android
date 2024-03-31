package com.makentoshe.screen.boorulist.mapper

import org.booruchan.extension.sdk.Source
import com.makentoshe.screen.boorulist.entity.SourceHealthUiState
import com.makentoshe.screen.boorulist.entity.SourceUiState
import javax.inject.Inject

class Source2SourceUiStateMapper @Inject constructor() {
    fun map(source: Source, availability: SourceHealthUiState = SourceHealthUiState.Loading) = SourceUiState(
        id = source.id,
        host = source.getHost(),
        title = source.title,
        healthState = availability,
    )

    private fun Source.getHost(): String = try {
        host
    } catch (exception: AbstractMethodError) {
        ""
    }
}