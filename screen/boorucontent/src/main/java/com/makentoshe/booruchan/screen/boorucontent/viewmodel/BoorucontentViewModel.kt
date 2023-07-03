package com.makentoshe.booruchan.screen.boorucontent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.makentoshe.booruchan.extension.BooruSource
import com.makentoshe.booruchan.extension.usecase.GetBooruSourceUseCase
import com.makentoshe.booruchan.extension.usecase.GetBooruSourcesUseCase
import com.makentoshe.booruchan.feature.context.BooruContext
import com.makentoshe.booruchan.feature.boorulist.domain.usecase.GetBooruContextUseCase
import com.makentoshe.booruchan.feature.search.BooruSearch
import com.makentoshe.booruchan.feature.search.SearchTag
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import com.makentoshe.booruchan.library.logging.internalLogInfo
import com.makentoshe.booruchan.library.logging.internalLogWarn
import com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.model.BooruPostPagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoorucontentViewModel @Inject constructor(
    private val getBooruSource: GetBooruSourceUseCase,
    private val booruPostPagingSourceFactory: BooruPostPagingSourceFactory,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    EventDelegate<BoorucontentScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<BoorucontentDestination> by DefaultNavigationDelegate(),
    StateDelegate<BoorucontentScreenState> by DefaultStateDelegate(BoorucontentScreenState.InitialState) {

    private val booruSourceStateFlow = MutableStateFlow<BooruSource?>(null)

    fun handleEvent(event: BoorucontentScreenEvent) = when (event) {
        is BoorucontentScreenEvent.Initialize -> initializeEvent(event)
        is BoorucontentScreenEvent.NavigationBack -> navigationBackEvent()
        is BoorucontentScreenEvent.Search -> searchEvent(event)
        is BoorucontentScreenEvent.Autocomplete -> autocompleteEvent(event)
    }

    private fun initializeEvent(event: BoorucontentScreenEvent.Initialize) {
        internalLogInfo("initialize event invoked: $event")

        // Skip initialization if it was already finished
        // This may happen after lock-unlock phone screen
        if (booruSourceStateFlow.value != null) return

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler { throwable ->
            internalLogWarn(throwable.toString())
            updateState { copy(toolbarState = BoorucontentToolbarState.Error(throwable.toString())) }
        }) {
            getBooruSource(event.booruSourceId).collectLatest(::onGetBooruSource)
        }
    }

    private fun onGetBooruSource(booruSource: BooruSource) {
        internalLogInfo("On get booru context success: $booruSource")

        // store boorucontext in viewmodel
        viewModelScope.launch { booruSourceStateFlow.emit(booruSource) }

        // show booru title
        updateState {
            copy(
                toolbarState = BoorucontentToolbarState.Content(booruSource.context.title),
                bottomSheetState = BoorucontentBottomSheetState()
            )
        }

        // prepare pager for displaying booru posts
        val postSearchFactory = booruSource.postSearchFactory
        val pagerFlow = Pager(PagingConfig(pageSize = postSearchFactory.requestedPostsPerPageCount)) {
            booruPostPagingSourceFactory.build(postSearchFactory, BooruSearch(emptySet()))
        }.flow.cachedIn(viewModelScope)

        updateState { copy(pagerFlow = pagerFlow) }
    }

    private fun searchEvent(event: BoorucontentScreenEvent.Search) {
        internalLogInfo("search event invoked: $event")

        val booruSource = booruSourceStateFlow.value ?: return
        val postSearchFactory = booruSource.postSearchFactory

        // Map search query to list of tags
        val searchTags = event.searchQuery.split(postSearchFactory.searchTagSeparator).map(::SearchTag)
        val booruSearch = BooruSearch(tags = searchTags.toSet())

        // Create new pager for displaying booru posts
        val pagerFlow = Pager(PagingConfig(pageSize = postSearchFactory.requestedPostsPerPageCount)) {
            booruPostPagingSourceFactory.build(postSearchFactory, booruSearch)
        }.flow.cachedIn(viewModelScope)

        updateState { copy(pagerFlow = pagerFlow) }
    }

    private fun autocompleteEvent(event: BoorucontentScreenEvent.Autocomplete) {
        internalLogInfo("autocomplete event invoked: $event")

        viewModelScope.launch {
            delay(1000)
            updateState { copy(bottomSheetState = bottomSheetState.copy(queryAutocomplete = (0..10).map { "${event.autocompleteQuery}-$it" })) }
        }
    }

    private fun navigationBackEvent() {
        internalLogInfo("navigation back event invoked")
        updateNavigation { BoorucontentDestination.BackDestination }
    }

}