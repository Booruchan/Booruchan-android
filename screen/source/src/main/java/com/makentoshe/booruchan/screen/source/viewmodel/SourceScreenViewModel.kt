@file:OptIn(ExperimentalMaterialApi::class)

package com.makentoshe.booruchan.screen.source.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.entity.TagType
import com.makentoshe.booruchan.feature.interactor.AutocompleteInteractor
import com.makentoshe.booruchan.feature.interactor.PluginInteractor
import com.makentoshe.booruchan.feature.usecase.GetTagByValueUseCase
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import com.makentoshe.booruchan.library.feature.throwable.Throwable2ThrowableEntityMapper
import com.makentoshe.booruchan.library.logging.internalLogError
import com.makentoshe.booruchan.library.logging.internalLogInfo
import com.makentoshe.booruchan.library.logging.internalLogWarn
import com.makentoshe.library.uikit.entity.TagTypeUiState
import com.makentoshe.library.uikit.entity.TagUiState
import com.makentoshe.booruchan.screen.source.mapper.Autocomplete2AutocompleteUiStateMapper
import com.makentoshe.booruchan.screen.source.mapper.Tag2TagUiStateMapper
import com.makentoshe.booruchan.screen.source.paging.PagingSourceFactory
import com.makentoshe.library.uikit.component.rating.RatingSegmentedButtonState
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

    private val getTagByValue: GetTagByValueUseCase,

    private val pagingSourceFactory: PagingSourceFactory,
    private val tag2TagUiStateMapper: Tag2TagUiStateMapper,
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
        // Navigation events
        is SourceScreenEvent.NavigationBack -> navigationBack()
        is SourceScreenEvent.NavigationImage -> navigationImage(event)

        is SourceScreenEvent.SearchValueChange -> searchValueChange(event)
        is SourceScreenEvent.SearchTagAdd -> searchAddTag(event)
        is SourceScreenEvent.SearchTagRemove -> searchRemoveTag(event)
        is SourceScreenEvent.SearchTagChangeRating -> searchChangeTagRating(event)
        is SourceScreenEvent.SearchApplyFilters -> searchApplyFilters()

        SourceScreenEvent.ShowSearch -> showSearchViaFullScreen()
        SourceScreenEvent.DismissSearch -> dismissSearchViaFullScreen()

        is SourceScreenEvent.ShowSnackbar -> showErrorViaSnackbar(event)
        SourceScreenEvent.DismissSnackbar -> dismissSnackbar()

        is SourceScreenEvent.SuggestedItemClicked -> suggestedItemClicked(event)
    }

    private fun initialize(event: SourceScreenEvent.Initialize) {
        if (pluginInteractor.sourceFlow.value !is EmptySource) {
            return // skip initialization if source already defined
        }

        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke initialize for Source(${event.sourceId})")
            // store arguments in the state
            updateState { copy(sourceId = event.sourceId) }
            // getting source by id or null
            pluginInteractor.getSourceById(event.sourceId)
                ?: return@launch updateState { copy(contentState = pluginSourceNullContentState()) }
        }
    }

    private fun onSource(source: Source) = viewModelScope.launch(Dispatchers.IO) {
        // Ignore empty source which is applied on initial
        if (source is EmptySource) return@launch
        // Show source title
        updateState { copy(sourceTitle = source.title) }

        onSourceRatingTagContent(source)
        onSourcePaginationContent(source)
    }

    private fun onSourcePaginationContent(source: Source) {
        // get fetch posts factory or show failure state
        val fetchPostsFactory = source.fetchPostsFactory
            ?: return updateState { copy(contentState = pluginFetchPostFactoryNullContentState()) }

        // create pager with default query
        val pagerFlow = Pager(PagingConfig(pageSize = fetchPostsFactory.requestedPostsPerPageCount)) {
            pagingSourceFactory.buildPostPagingSource(source = source, fetchPostsFactory, query = "")
        }.flow.cachedIn(viewModelScope)

        // Update state
        updateState {
            copy(contentState = ContentState.Success(pagerFlow = pagerFlow))
        }
    }

    private fun onSourceRatingTagContent(source: Source) {
        // Get first 3 rating tag values (we could not handle more than 3 yet)
        val ratingTagValues = (source.settings.ratingTagSettings?.values ?: emptyList()).subList(0, 3)

        // Show rating tag content state
        updateState {
            copy(
                ratingTagContentState = SourceScreenRatingTagContentState(
                    visible = false, ratingTagSegmentedButtonState = RatingSegmentedButtonState(
                        values = ratingTagValues,
                    )
                )
            )
        }
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

    private fun searchValueChange(event: SourceScreenEvent.SearchValueChange) {
        val source = pluginInteractor.sourceFlow.value
        internalLogInfo("invoke search value change: ${event.value}")
        // Symbols that indicates a new tag or separation between tags
        val searchTagSymbols = source.settings.searchSettings.searchTags
        // Get last input character
        val searchValueLastCharacter = event.value.lastOrNull()?.toString()

        // Check is the last character indicates a new tag input
        if (searchValueLastCharacter != null && searchTagSymbols.contains(searchValueLastCharacter) && event.value.count() > 2) {
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
        val source = pluginInteractor.sourceFlow.value
        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke search add tag: ${event.tag}")
            // skip any blank input: we're not interested in it
            if (event.tag.isBlank()) return@launch internalLogInfo("skip add tag: ${event.tag}")

            // Get tag info from database to try to define type
            val sourceTag = getTagByValue(source.id, event.tag)
            val tag = if (sourceTag != null && sourceTag.type != TagType.Other) {
                sourceTag
            } else {
                getTagByValue(event.tag).firstOrNull { it.type != TagType.Other }
            }

            // Create ui state for tag
            val tagUiState = tag?.let(tag2TagUiStateMapper::map)
                ?: TagUiState(tag = event.tag, type = TagTypeUiState.General)

            // Append new tag to current tags, hide autocompletion and clear input field
            updateState {
                copy(searchState = searchState.copy(value = "", autocompleteState = AutocompleteState.None))
            }

            // Add tag to the specific content
            when (tagUiState.type) {
                TagTypeUiState.General, TagTypeUiState.Other -> updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            generalTagsContentState = tagsComponentState.generalTagsContentState.copy(
                                visible = true, tags = tagsComponentState.generalTagsContentState.tags.plus(tagUiState),
                            )
                        )
                    )
                }

                TagTypeUiState.Artist -> updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            artistTagsContentState = tagsComponentState.artistTagsContentState.copy(
                                visible = true, tags = tagsComponentState.artistTagsContentState.tags.plus(tagUiState),
                            )
                        )
                    )
                }

                TagTypeUiState.Character -> updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            characterTagsContentState = tagsComponentState.characterTagsContentState.copy(
                                visible = true,
                                tags = tagsComponentState.characterTagsContentState.tags.plus(tagUiState),
                            ),
                        )
                    )
                }

                TagTypeUiState.Copyright -> updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            copyrightTagsContentState = tagsComponentState.copyrightTagsContentState.copy(
                                visible = true,
                                tags = tagsComponentState.copyrightTagsContentState.tags.plus(tagUiState),
                            ),
                        )
                    )
                }

                TagTypeUiState.Metadata -> updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            metadataTagsContentState = tagsComponentState.metadataTagsContentState.copy(
                                visible = true,
                                tags = tagsComponentState.metadataTagsContentState.tags.plus(tagUiState),
                            ),
                        )
                    )
                }
            }
        }
    }

    private fun searchChangeTagRating(event: SourceScreenEvent.SearchTagChangeRating) {
        val source = pluginInteractor.sourceFlow.value

        internalLogInfo("invoke change rating tag")

        val ratingTagSettings =
            source.settings.ratingTagSettings ?: return internalLogError("Settings for \"rating\" tag were not define")

        // Get current selections list
        val selected = state.ratingTagContentState.ratingTagSegmentedButtonState.selected.toMutableList()

        // Change selection
        if (selected.contains(event.index)) {
            selected.remove(event.index)
        } else {
            selected.add(event.index)
        }

        // Update selection state
        updateState {
            copy(
                ratingTagContentState = ratingTagContentState.copy(
                    ratingTagSegmentedButtonState = ratingTagContentState.ratingTagSegmentedButtonState.copy(
                        selected = selected,
                    )
                ),
            )
        }
    }

    private fun searchApplyFilters() {
        viewModelScope.launch(
            context = Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                updateState { copy(contentState = failureContentState(throwable)) }
            },
        ) {
            internalLogInfo("invoke apply filters event")

            val source = pluginInteractor.sourceFlow.value
            if (source is EmptySource) return@launch updateState { copy(contentState = pluginSourceNullContentState()) }

            // get fetch posts factory or show failure state
            val fetchPostsFactory = source.fetchPostsFactory
                ?: return@launch updateState { copy(contentState = pluginFetchPostFactoryNullContentState()) }

            // Collect tags from groups
            val generalTags = state.tagsComponentState.generalTagsContentState.tags
            val characterTags = state.tagsComponentState.characterTagsContentState.tags
            val artistTags = state.tagsComponentState.artistTagsContentState.tags
            val copyrightTags = state.tagsComponentState.copyrightTagsContentState.tags
            val metadataTags = state.tagsComponentState.metadataTagsContentState.tags

            // Join all tags together
            val tagsQuery =
                (generalTags + characterTags + artistTags + copyrightTags + metadataTags).joinToString(fetchPostsFactory.searchTagSeparator) { it.tag }

            val pagerFlow = Pager(PagingConfig(pageSize = fetchPostsFactory.requestedPostsPerPageCount)) {
                pagingSourceFactory.buildPostPagingSource(source = source, fetchPostsFactory, tagsQuery)
            }.flow.cachedIn(viewModelScope)

            updateState {
                copy(contentState = ContentState.Success(pagerFlow = pagerFlow))
            }
        }
    }

    private fun searchRemoveTag(event: SourceScreenEvent.SearchTagRemove) {
        viewModelScope.launch(Dispatchers.IO) {
            internalLogInfo("invoke remove tag: ${event.tag}")

            // Remove from general tags
            val generalTag = state.tagsComponentState.generalTagsContentState.tags.firstOrNull { it.tag == event.tag }
            if (generalTag != null) {
                val newGeneralTags =
                    state.tagsComponentState.generalTagsContentState.tags.filterNot { it.tag == event.tag }.toSet()
                return@launch updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            generalTagsContentState = tagsComponentState.generalTagsContentState.copy(
                                visible = newGeneralTags.isNotEmpty(), tags = newGeneralTags,
                            )
                        )
                    )
                }
            }

            // Remove from artist tags
            val artistTag = state.tagsComponentState.artistTagsContentState.tags.firstOrNull { it.tag == event.tag }
            if (artistTag != null) {
                val newArtistTags =
                    state.tagsComponentState.artistTagsContentState.tags.filterNot { it.tag == event.tag }.toSet()
                return@launch updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            artistTagsContentState = tagsComponentState.artistTagsContentState.copy(
                                visible = newArtistTags.isNotEmpty(), tags = newArtistTags,
                            )
                        )
                    )
                }
            }

            // Remove from character tags
            val characterTag =
                state.tagsComponentState.characterTagsContentState.tags.firstOrNull { it.tag == event.tag }
            if (characterTag != null) {
                val newCharacterTags =
                    state.tagsComponentState.characterTagsContentState.tags.filterNot { it.tag == event.tag }.toSet()
                return@launch updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            characterTagsContentState = tagsComponentState.characterTagsContentState.copy(
                                visible = newCharacterTags.isNotEmpty(), tags = newCharacterTags,
                            )
                        )
                    )
                }
            }

            // Remove from copyright tags
            val copyrightTag =
                state.tagsComponentState.copyrightTagsContentState.tags.firstOrNull { it.tag == event.tag }
            if (copyrightTag != null) {
                val newCopyrightTag =
                    state.tagsComponentState.copyrightTagsContentState.tags.filterNot { it.tag == event.tag }.toSet()
                return@launch updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            copyrightTagsContentState = tagsComponentState.copyrightTagsContentState.copy(
                                visible = newCopyrightTag.isNotEmpty(), tags = newCopyrightTag,
                            )
                        )
                    )
                }
            }

            val metadataTag = state.tagsComponentState.metadataTagsContentState.tags.firstOrNull { it.tag == event.tag }
            if (metadataTag != null) {
                val newMetadataTag =
                    state.tagsComponentState.metadataTagsContentState.tags.filterNot { it.tag == event.tag }.toSet()
                return@launch updateState {
                    copy(
                        tagsComponentState = tagsComponentState.copy(
                            metadataTagsContentState = tagsComponentState.metadataTagsContentState.copy(
                                visible = newMetadataTag.isNotEmpty(), tags = newMetadataTag,
                            ),
                        )
                    )
                }
            }

            return@launch internalLogWarn("could not find tag: ${event.tag}")
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

    private fun showErrorViaSnackbar(event: SourceScreenEvent.ShowSnackbar) {
        val throwableEntity = throwable2ThrowableEntityMapper.map(event.throwable)
        val message = throwableEntity.description.takeIf { it.isNotBlank() } ?: throwableEntity.title

        updateState { copy(snackbarState = SnackbackState.Content(message = message)) }
    }

    private fun dismissSnackbar() {
        updateState { copy(snackbarState = SnackbackState.None) }
    }

    private fun showSearchViaFullScreen() {
        updateState { copy(searchState = searchState.copy(fullScreenState = SourceScreenSearchState.FullScreenState.Expanded)) }
    }

    private fun dismissSearchViaFullScreen() {
        updateState { copy(searchState = searchState.copy(fullScreenState = SourceScreenSearchState.FullScreenState.Collapsed)) }
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
            title = "There is an plugin error", description = "Could not determine FetchPostFactory for this Source"
        )
    }

    private val SourceSearchSettings.searchTags: List<String>
        get() = listOf(searchTagAnd, searchTagOr, searchTagNot)
}
