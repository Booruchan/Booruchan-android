package com.makentoshe.booruchan.feature.interactor

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.InitialSource
import com.makentoshe.booruchan.feature.PluginFactory
import com.makentoshe.booruchan.feature.usecase.GetAllPluginsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

typealias SourceId = String

class PluginInteractor @Inject constructor(
    private val pluginFactory: PluginFactory,
    private val findAllPlugins: GetAllPluginsUseCase,
) {

    /** Default initial source. This source indicates that source was not declared */
    private val internalSourceFlow = MutableStateFlow<Source>(InitialSource)

    val sourceFlow: StateFlow<Source> get() = internalSourceFlow

    /** find source from plugin by source id and publish it in [sourceFlow] */
    suspend fun getSourceById(sourceId: SourceId): Source? {
        val source = findAllPlugins().map(pluginFactory::buildSource).find { source -> source?.id == sourceId }
        return source?.also { internalSourceFlow.emit(it) }
    }

    suspend fun requestSourceById(sourceId: SourceId) {
        val plugins = findAllPlugins()
        val sources = plugins.map(pluginFactory::buildSource)
        val source = sources.find { source -> source?.id == sourceId }

        if (source != null) internalSourceFlow.emit(source)
    }

}