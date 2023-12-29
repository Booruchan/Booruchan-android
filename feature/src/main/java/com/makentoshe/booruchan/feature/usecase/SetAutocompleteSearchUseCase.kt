package com.makentoshe.booruchan.feature.usecase

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.feature.mapper.Autocomplete2DatabaseTagMapper
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import javax.inject.Inject

class SetAutocompleteSearchUseCase @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val mapper: Autocomplete2DatabaseTagMapper,
) {
    suspend operator fun invoke(source: Source, autocomplete: Autocomplete) {
        applicationDatabase.tagDao().insert(mapper.map(source, autocomplete))
    }
}