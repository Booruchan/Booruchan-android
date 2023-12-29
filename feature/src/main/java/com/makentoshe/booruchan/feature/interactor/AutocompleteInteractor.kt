package com.makentoshe.booruchan.feature.interactor

import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.entity.Autocomplete
import com.makentoshe.booruchan.feature.exception.FetchAutocompleteEmptyException
import com.makentoshe.booruchan.feature.exception.FetchAutocompleteFactoryException
import com.makentoshe.booruchan.feature.exception.FetchAutocompleteSourceException
import com.makentoshe.booruchan.feature.usecase.FetchAutocompleteSearchUseCase
import com.makentoshe.booruchan.feature.usecase.SetAutocompleteSearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.factory.AutocompleteSearchFactory
import javax.inject.Inject

class AutocompleteInteractor @Inject constructor(
    private val fetchAutocompleteSearch: FetchAutocompleteSearchUseCase,
    private val setAutocompleteSearch: SetAutocompleteSearchUseCase,
) {

    private var autocompleteJob: Job? = null

    private val internalAutocompleteFlow = MutableStateFlow<State>(State.None(""))
    val autocompleteFlow: StateFlow<State> get() = internalAutocompleteFlow

    /** Fetch autocompletions from source */
    suspend fun fetchAutocomplete(source: Source, autocompleteSearchValue: String) {
        withContext(Dispatchers.IO) {
            // Cancel previous autocomplete job
            autocompleteJob?.cancel()
            // Clear autocomplete flow
            internalAutocompleteFlow.emit(State.None(autocompleteSearchValue))
            // launch new autocomplete job
            autocompleteJob = launch { fetchAutocompleteJob(source, autocompleteSearchValue) }
        }
    }

    private suspend fun fetchAutocompleteJob(source: Source, autocompleteSearchValue: String) {
        // Check source is not empty
        if (source is EmptySource) throw FetchAutocompleteSourceException()
        // Check autocompletion is implemented
        val autocompleteSearchFactory = source.autocompleteSearchFactory ?: throw FetchAutocompleteFactoryException()
        // Check query is not empty
        if (autocompleteSearchValue.isEmpty()) throw FetchAutocompleteEmptyException()
        // delay between input and autocomplete starting
        delay(350)
        // Start loading
        internalAutocompleteFlow.emit(State.Loading(autocompleteSearchValue))
        // Execute autocompletion
        fetchAutocompleteJob(source, autocompleteSearchFactory, autocompleteSearchValue)
    }

    private suspend fun fetchAutocompleteJob(source: Source, factory: AutocompleteSearchFactory, value: String) {
        // request autocompletion
        val request = AutocompleteSearchFactory.AutocompleteSearchRequest(value)
        val autocompletes = fetchAutocompleteSearch(factory, request)
        // invoke flow on new autocomplete values
        internalAutocompleteFlow.emit(State.Content(value, autocompletes))
        // Store autocompletes in the database
        autocompletes.forEach { autocomplete -> setAutocompleteSearch(source, autocomplete) }
    }

    sealed interface State {
        val value: String
        data class None(override val value: String): State
        data class Loading(override val value: String): State
        data class Content(override val value: String, val autocompletes: List<Autocomplete>): State
    }
}