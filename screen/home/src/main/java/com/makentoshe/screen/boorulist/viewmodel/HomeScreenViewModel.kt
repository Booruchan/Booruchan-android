package com.makentoshe.screen.boorulist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.PluginFactory
import com.makentoshe.booruchan.feature.healthcheck.HealthcheckUseCase
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
import com.makentoshe.booruchan.feature.usecase.GetAllPluginsUseCase
import com.makentoshe.screen.boorulist.entity.SourceHealthUiState
import com.makentoshe.screen.boorulist.mapper.Source2SourceUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val pluginFactory: PluginFactory,

    private val findAllPlugins: GetAllPluginsUseCase,
    private val healthcheckUseCase: HealthcheckUseCase,

    private val source2SourceUiStateMapper: Source2SourceUiStateMapper,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<HomeScreenState> by DefaultStateDelegate(HomeScreenState.InitialState),
    EventDelegate<HomeScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<HomeScreenDestination> by DefaultNavigationDelegate() {

    private val _sourcesSharedFlow: MutableSharedFlow<List<Source>> = MutableSharedFlow()
    private val sourcesSharedFlow: SharedFlow<List<Source>> get() = _sourcesSharedFlow

    init {
        viewModelScope.iolaunch {
            sourcesSharedFlow.collectLatest(::onSourcesCollect)
        }
    }

    override fun handleEvent(event: HomeScreenEvent) = when (event) {
        is HomeScreenEvent.Initialize -> initialize(event)
        is HomeScreenEvent.NavigationSource -> navigateSource(event)
        HomeScreenEvent.RefreshPlugins -> refreshPlugins()
    }

    private fun initialize(event: HomeScreenEvent.Initialize) {
        internalLogInfo("invoke event: $event")

        if (state.sourcePageState.content !is HomeScreenSourcePageContent.None) {
            return internalLogInfo("$event event: abandon")
        }

        updateState {
            copy(sourcePageState = sourcePageState.copy(title = "Sources"))
        }

        initializeSource(event)

//        viewModelScope.iolaunch(Dispatchers.IO) {
//            val plugins = findAllPlugins()
//            internalLogInfo("continue event: found ${plugins.count()} plugins")
//
//            val sources = plugins.mapNotNull(pluginFactory::buildSource).onEach(::onHealthCheckSource)
//            val sourceUiList = sources.map(source2SourceUiStateMapper::map)
//            updateState {
//                copy(pluginContent = HomeScreenSourcePage2.Content(sources = sourceUiList, refreshing = false))
//            }
//        }
    }

    private fun initializeSource(event: HomeScreenEvent.Initialize) {
        updateState {
            copy(
                sourcePageState = sourcePageState.copy(
                    content = HomeScreenSourcePageContent.Loading,
                )
            )
        }

        viewModelScope.iolaunch(Dispatchers.IO) {
            val plugins = findAllPlugins()
            internalLogInfo("$event event: found ${plugins.count()} plugins")

            _sourcesSharedFlow.emit(plugins.mapNotNull(pluginFactory::buildSource))
        }
    }

    private fun onSourcesCollect(sources: List<Source>) {
        internalLogInfo("Collect Sources: $sources")
        sources.onEach(::onHealthCheckSource)

        val sourceUiList = sources.map(source2SourceUiStateMapper::map)
        updateState {
            copy(
                sourcePageState = sourcePageState.copy(
                    content = HomeScreenSourcePageContent.Content(
                        refreshing = false,
                        sources = sourceUiList,
                    )
                )
            )
        }
    }

    private fun onHealthCheckSource(source: Source) {
        viewModelScope.iolaunch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            onHealthCheckFailure(source, throwable)
        }) {
            val factory = source.healthCheckFactory
                ?: throw IllegalStateException("health check factory is null")

            onHealthCheckSuccess(source, healthcheckUseCase(factory.buildRequest()))
        }
    }

    private fun onHealthCheckFailure(source: Source, throwable: Throwable) {
        internalLogWarn("Healthcheck(${source.id}): $throwable")
        updateSourceUiStateHealthCheck(source, SourceHealthUiState.Unavailable)
    }

    private fun onHealthCheckSuccess(source: Source, isAvailabile: Boolean) {
        internalLogInfo("Healthcheck(${source.id}): isAvailable=$isAvailabile")
        if (isAvailabile) {
            updateSourceUiStateHealthCheck(source, SourceHealthUiState.Available)
        } else {
            updateSourceUiStateHealthCheck(source, SourceHealthUiState.Unavailable)
        }
    }

    private fun updateSourceUiStateHealthCheck(source: Source, availability: SourceHealthUiState) = updateState {
        val sourcePageStateContent = state.sourcePageState.content

        // Check current state is Content
        // We assume that health state updating request will be called
        // right after content will be displayed, so in this case exception will never be thrown
        val content = (sourcePageStateContent as? HomeScreenSourcePageContent.Content)
            ?: throw IllegalStateException(state.toString())

        // O(n^2) but we don't care. This list just cant contain more that 100-200 items
        val sources = content.sources.map { sourceUiState ->
            if (sourceUiState.id != source.id) return@map sourceUiState else {
                return@map sourceUiState.copy(healthState = availability)
            }
        }

        copy(
            sourcePageState = sourcePageState.copy(
                content = HomeScreenSourcePageContent.Content(sources = sources, refreshing = false)
            )
        )
    }

    private fun refreshPlugins() {
        internalLogInfo("refresh plugins invoked")

        viewModelScope.iolaunch(Dispatchers.IO) {
            val plugins = findAllPlugins()
            internalLogInfo("Found ${plugins.count()} plugins")

            _sourcesSharedFlow.emit(plugins.mapNotNull(pluginFactory::buildSource))
        }
    }

    private fun navigateSource(event: HomeScreenEvent.NavigationSource) {
        updateNavigation { HomeScreenDestination.SourceDestination(sourceId = event.sourceId) }
    }
}