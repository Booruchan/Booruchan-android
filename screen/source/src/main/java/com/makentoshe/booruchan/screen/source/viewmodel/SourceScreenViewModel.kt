@file:OptIn(ExperimentalMaterialApi::class)

package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.entity.ActionSearchHistory
import com.makentoshe.booruchan.feature.interactor.AutocompleteInteractor
import com.makentoshe.booruchan.feature.interactor.PluginInteractor
import com.makentoshe.booruchan.feature.usecase.SetActionSearchHistoryUseCase
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import com.makentoshe.booruchan.library.feature.throwable.Throwable2ThrowableEntityMapper
import com.makentoshe.booruchan.library.logging.internalLogInfo
import com.makentoshe.booruchan.library.logging.internalLogWarn
import com.makentoshe.booruchan.screen.source.entity.TagType
import com.makentoshe.booruchan.screen.source.entity.TagUiState
import com.makentoshe.booruchan.screen.source.mapper.Autocomplete2AutocompleteUiStateMapper
import com.makentoshe.booruchan.screen.source.paging.PagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.booruchan.extension.sdk.Source
import org.booruchan.extension.sdk.settings.SourceSearchSettings
import javax.inject.Inject

@HiltViewModel
class SourceScreenViewModel @Inject constructor(
    private val pluginInteractor: PluginInteractor,
    private val autocompleteInteractor: AutocompleteInteractor,

    private val setActionSearchHistory: SetActionSearchHistoryUseCase,
    private val pagingSourceFactory: PagingSourceFactory,
    private val autocompleteUiStateMapper: Autocomplete2AutocompleteUiStateMapper,
    private val throwable2ThrowableEntityMapper: Throwable2ThrowableEntityMapper,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<SourceScreenState> by DefaultStateDelegate(SourceScreenState.InitialState),
    EventDelegate<SourceScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<SourceScreenDestination> by DefaultNavigationDelegate() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            pluginInteractor.sourceFlow.collectLatest(::onSource)
        }
        viewModelScope.launch(Dispatchers.IO) {
            autocompleteInteractor.autocompleteFlow.collectLatest(::onAutocomplete)
        }
    }

    override fun handleEvent(event: SourceScreenEvent) = when (event) {
        is SourceScreenEvent.Initialize -> initialize(event)
        is SourceScreenEvent.NavigationBack -> navigationBack()
        is SourceScreenEvent.NavigationImage -> navigationImage(event)
        is SourceScreenEvent.SearchValueChange -> searchValueChange(event)
        is SourceScreenEvent.SearchTagAdd -> searchAddTag(event)
        is SourceScreenEvent.SearchTagRemove -> searchRemoveTag(event)
        is SourceScreenEvent.SearchApplyFilters -> searchApplyFilters()
        is SourceScreenEvent.StoreSourceSearch -> storeSourceSearch()
        SourceScreenEvent.ShowSearch -> showSearchViaFullScreen()
        SourceScreenEvent.DismissSearch -> dismissSearchViaFullScreen()
        is SourceScreenEvent.ShowSnackbar -> showErrorViaSnackbar(event)
        SourceScreenEvent.DismissSnackbar -> dismissSnackbar()
        is SourceScreenEvent.SuggestedItemClicked -> suggestedItemClicked(event)
    }

    private fun initialize(event: SourceScreenEvent.Initialize) {
        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke initialize for Source(${event.sourceId})")
            // store arguments in the state
            updateState { copy(sourceId = event.sourceId) }
            // getting source by id or null
            pluginInteractor.getSourceById(event.sourceId)
                ?: return@launch updateState { copy(contentState = pluginSourceNullContentState()) }
        }
    }

    private suspend fun onSource(source: Source) = viewModelScope.launch(Dispatchers.IO) {
        // Ignore empty source which is applied on initial
        if (source is EmptySource) return@launch
        // Show source title
        updateState { copy(sourceTitle = source.title) }

        // get fetch posts factory or show failure state
        val fetchPostsFactory = source.fetchPostsFactory
            ?: return@launch updateState { copy(contentState = pluginFetchPostFactoryNullContentState()) }

        val pagerFlow = Pager(PagingConfig(pageSize = fetchPostsFactory.requestedPostsPerPageCount)) {
            pagingSourceFactory.buildPostPagingSource(source = source, fetchPostsFactory, query = "")
        }.flow.cachedIn(viewModelScope)

        updateState {
            copy(contentState = ContentState.Success(pagerFlow = pagerFlow))
        }

        storeSourceSearch()
    }

    private fun onAutocomplete(autocompleteState: AutocompleteInteractor.State) = when (autocompleteState) {
        is AutocompleteInteractor.State.None -> updateState {
            internalLogInfo("autocomplete None state")
            copy(
                searchState = searchState.copy(
                    value = autocompleteState.value,
                    autocompleteState = AutocompleteState.None,
                )
            )
        }

        is AutocompleteInteractor.State.Loading -> updateState {
            internalLogInfo("autocomplete Loading state")
            copy(
                searchState = searchState.copy(
                    value = autocompleteState.value,
                    autocompleteState = AutocompleteState.Loading,
                )
            )
        }

        is AutocompleteInteractor.State.Content -> updateState {
            internalLogInfo("autocomplete Content(${autocompleteState.autocompletes}) state")
            val autocompleteUiStates = autocompleteState.autocompletes.map(autocompleteUiStateMapper::map).toSet()
            copy(
                searchState = searchState.copy(
                    value = autocompleteState.value,
                    autocompleteState = AutocompleteState.Content(autocompleteUiStates),
                )
            )
        }
    }

    private fun navigationBack() {
        internalLogInfo("invoke navigation back")
        updateNavigation { SourceScreenDestination.BackDestination }
    }

    private fun navigationImage(event: SourceScreenEvent.NavigationImage) {
        val source = pluginInteractor.sourceFlow.value
        internalLogInfo("invoke navigation image for Source(${source.id}) with Post(${event.id})")
        updateNavigation { SourceScreenDestination.ImageDestination(postId = event.id, sourceId = source.id) }
    }

    private fun searchValueChange(event: SourceScreenEvent.SearchValueChange) {
        val source = pluginInteractor.sourceFlow.value
        internalLogInfo("invoke search value change: ${event.value}")

        val searchValueLastCharacter = event.value.lastOrNull()?.toString()
        val searchTags = source.settings.searchSettings.searchTags

        if (searchValueLastCharacter != null && searchTags.contains(searchValueLastCharacter) && event.value.count() > 2) {
            handleEvent(SourceScreenEvent.SearchTagAdd(event.value))
            return updateState {
                copy(searchState = searchState.copy(value = "", autocompleteState = AutocompleteState.None))
            }
        }

        updateState {
            copy(searchState = searchState.copy(value = event.value, autocompleteState = AutocompleteState.None))
        }

        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            internalLogWarn(throwable.message ?: "")
        }) {
            autocompleteInteractor.fetchAutocomplete(source, event.value)
        }
    }

    private fun suggestedItemClicked(event: SourceScreenEvent.SuggestedItemClicked) {
        val source = pluginInteractor.sourceFlow.value
        internalLogInfo("invoke suggested item clicked: $event")

        // Add tag from the suggestion list and apply login search operator if presented
        val searchValueFirstCharacter = state.searchState.value.firstOrNull()?.toString()
        if (source.settings.searchSettings.searchTags.contains(searchValueFirstCharacter)) {
            searchAddTag(SourceScreenEvent.SearchTagAdd("$searchValueFirstCharacter${event.value}"))
        } else {
            searchAddTag(SourceScreenEvent.SearchTagAdd(event.value))
        }
    }

    private fun searchAddTag(event: SourceScreenEvent.SearchTagAdd) {
        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke search add tag: ${event.tag}")
            // skip any blank input: we're not interested in it
            if (event.tag.isBlank()) return@launch internalLogInfo("skip add tag: ${event.tag}")
            // General is a default type for tag
            val tagUiState = TagUiState(tag = event.tag, type = TagType.General)
            // Append new tag to current tags, hide autocompletion and clear input field
            updateState {
                copy(
                    searchState = searchState.copy(
                        value = "",
                        tags = searchState.tags.plus(tagUiState),
                        autocompleteState = AutocompleteState.None
                    ),
                )
            }
        }
    }

    private fun storeSourceSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            val source = pluginInteractor.sourceFlow.value
            internalLogInfo("invoke apply filters event: ${state.searchState.tags}")

            // get fetch posts factory or do nothing
            val fetchPostsFactory = source.fetchPostsFactory ?: return@launch

            val tags = state.searchState.tags.joinToString(separator = fetchPostsFactory.searchTagSeparator) { it.tag }
            val sourceSearchNavigation = ActionSearchHistory(source = source.id, search = tags)
            setActionSearchHistory(sourceSearchNavigation)
        }
    }

    private fun searchApplyFilters() {
        viewModelScope.launch(
            context = Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                updateState { copy(contentState = failureContentState(throwable)) }
            },
        ) {
            val source = pluginInteractor.sourceFlow.value
            internalLogInfo("invoke apply filters event: ${state.searchState.tags}")

            if (source is EmptySource) {
                return@launch updateState { copy(contentState = pluginSourceNullContentState()) }
            }

            // get fetch posts factory or show failure state
            val fetchPostsFactory = source.fetchPostsFactory
                ?: return@launch updateState { copy(contentState = pluginFetchPostFactoryNullContentState()) }

            val query = state.searchState.tags.joinToString(fetchPostsFactory.searchTagSeparator) { it.tag }

            val pagerFlow = Pager(PagingConfig(pageSize = fetchPostsFactory.requestedPostsPerPageCount)) {
                pagingSourceFactory.buildPostPagingSource(source = source, fetchPostsFactory, query)
            }.flow.cachedIn(viewModelScope)

            updateState {
                copy(contentState = ContentState.Success(pagerFlow = pagerFlow))
            }

            storeSourceSearch()
        }
    }

    private fun searchRemoveTag(event: SourceScreenEvent.SearchTagRemove) {
        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke remove tag: ${event.tag}")

            val tagUiState = state.searchState.tags.find { it.tag == event.tag }
                ?: return@launch internalLogWarn("could not find tag: ${event.tag}")

            updateState { copy(searchState = searchState.copy(tags = searchState.tags.minus(tagUiState))) }
        }
    }

    private fun showErrorViaSnackbar(event: SourceScreenEvent.ShowSnackbar) {
        val throwableEntity = throwable2ThrowableEntityMapper.map(event.throwable)
        val message = throwableEntity.description.takeIf { it.isNotBlank() } ?: throwableEntity.title

        updateState { copy(snackbarState = SnackbackState.Content(message = message)) }
    }

    private fun dismissSnackbar() {
        updateState { copy(snackbarState = SnackbackState.None) }
    }

    private fun showSearchViaFullScreen() {
        updateState { copy(searchState = searchState.copy(fullScreenState = SearchState.FullScreenState.Expanded)) }
    }

    private fun dismissSearchViaFullScreen() {
        updateState { copy(searchState = searchState.copy(fullScreenState = SearchState.FullScreenState.Collapsed)) }
    }

    private fun failureContentState(throwable: Throwable): ContentState.Failure {
        val throwableEntity = throwable2ThrowableEntityMapper.map(throwable)
        return ContentState.Failure(title = throwableEntity.title, description = throwableEntity.description)
    }

    private fun pluginSourceNullContentState(): ContentState.Failure {
        return ContentState.Failure(
            title = "There is a plugin error",
            description = "Could not determine Source for this plugin",
        )
    }

    private fun pluginFetchPostFactoryNullContentState(): ContentState.Failure {
        return ContentState.Failure(
            title = "There is an plugin error",
            description = "Could not determine FetchPostFactory for this Source"
        )
    }

    private val SourceSearchSettings.searchTags: List<String>
        get() = listOf(searchTagAnd, searchTagOr, searchTagNot)
}
