package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.PluginFactory
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageScreenViewModel @Inject constructor(
    private val pluginFactory: PluginFactory,
    private val findAllPlugins: GetAllPluginsUseCase,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<ImageScreenState> by DefaultStateDelegate(ImageScreenState.InitialState),
    EventDelegate<ImageScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<ImageScreenDestination> by DefaultNavigationDelegate() {

    private val sourceFlow = MutableStateFlow<Source>(EmptySource)

    init {
        viewModelScope.launch {
            sourceFlow.collectLatest(::onSource)
        }
    }

    override fun handleEvent(event: ImageScreenEvent) = when (event) {
        is ImageScreenEvent.Initialize -> initialize(event)
    }

    private fun initialize(event: ImageScreenEvent.Initialize) = viewModelScope.iolaunch {
        internalLogInfo("invoke initialize for Source(${event.sourceId})")

        // find source from plugin by source id or show failure state
        val source = findAllPlugins().map(pluginFactory::buildSource).find { source -> source?.id == event.sourceId }
            ?: return@iolaunch //updateState { copy(contentState = pluginSourceNullContentState()) }

        sourceFlow.emit(source)
    }

    private fun onSource(source: Source) {
        if (source is EmptySource) return

        println(source)
    }
}