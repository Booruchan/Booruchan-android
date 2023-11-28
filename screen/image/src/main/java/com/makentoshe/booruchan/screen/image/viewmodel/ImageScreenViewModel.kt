package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.PluginFactory
import com.makentoshe.booruchan.feature.interactor.SourceInteractor
import com.makentoshe.booruchan.feature.usecase.GetPostByIdUseCase
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import com.makentoshe.booruchan.library.logging.internalLogInfo
import com.makentoshe.booruchan.library.plugin.GetAllPluginsUseCase
import com.makentoshe.booruchan.screen.image.mapper.Post2SamplePostUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageScreenViewModel @Inject constructor(
    private val sourceInteractor: SourceInteractor,

    private val getPost: GetPostByIdUseCase,
    private val post2SamplePostUiStateMapper: Post2SamplePostUiStateMapper,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<ImageScreenState> by DefaultStateDelegate(ImageScreenState.InitialState),
    EventDelegate<ImageScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<ImageScreenDestination> by DefaultNavigationDelegate() {

    init {
        viewModelScope.launch {
            sourceInteractor.sourceFlow.collectLatest(::onSource)
        }
    }

    override fun handleEvent(event: ImageScreenEvent) = when (event) {
        is ImageScreenEvent.Initialize -> initialize(event)
        ImageScreenEvent.Retry -> retry()
    }

    private fun initialize(event: ImageScreenEvent.Initialize) = viewModelScope.iolaunch {
        internalLogInfo("invoke initialize for Source(${event.sourceId})")
        // store arguments in the state
        updateState { copy(sourceId = event.sourceId, postId = event.postId) }
        // getting source by id or null
        sourceInteractor.getSourceById(event.sourceId)
            ?: return@iolaunch updateState { copy(contentState = pluginSourceNullContentState()) }
    }

    private suspend fun onSource(source: Source) = viewModelScope.launch(Dispatchers.IO) {
        // Ignore empty source which is applied on initial
        if (source is EmptySource) return@launch
        // Show source title
        updateState { copy(sourceTitle = source.title) }
        // Request post content
        requestSamplePostContent(state.sourceId, state.postId)
    }

    private suspend fun requestSamplePostContent(sourceId: String, postId: String) {
        // Receive post by its id
        val post = getPost(sourceId, postId)
            ?: return updateState { copy(contentState = sas()) }
        // Map post to ui state
        val samplePostUiState = post2SamplePostUiStateMapper.map(post)

        updateState {
            copy(contentState = ContentState.Content(samplePostUiState))
        }
    }

    private fun retry() = viewModelScope.iolaunch {
        updateState { copy(contentState = ContentState.Loading) }
        // Request post content again
        requestSamplePostContent(state.sourceId, state.postId)
    }

    private fun sas() = ContentState.Failure(
        title = "Could not retrieve post",
        description = "",
        button = "Retry",
        event = ImageScreenEvent.Retry,
    )

    private fun pluginSourceNullContentState() = ContentState.Failure(
        title = "There is a plugin error",
        description = "Could not determine Source for this plugin",
        button = null,
        event = null,
    )
}
