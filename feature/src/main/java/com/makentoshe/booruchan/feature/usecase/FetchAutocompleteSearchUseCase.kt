package com.makentoshe.booruchan.feature.usecase

import org.booruchan.extension.sdk.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.feature.mapper.NetworkAutocomplete2AutocompleteMapper
import com.makentoshe.booruchan.feature.network.NetworkRepository
import javax.inject.Inject

class FetchAutocompleteSearchUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val networkMapper: NetworkAutocomplete2AutocompleteMapper,
) {
    suspend operator fun invoke(
        autocompleteSearchFactory: AutocompleteSearchFactory,
        request: AutocompleteSearchFactory.AutocompleteSearchRequest,
    ): List<Autocomplete> {
        val networkRequest = autocompleteSearchFactory.buildRequest(request = request)
        val networkResponse = networkRepository.execute(request = networkRequest)
        val networkAutocompletes = autocompleteSearchFactory.parseResponse(response = networkResponse)
        return networkAutocompletes.map(networkMapper::map)
    }
}
