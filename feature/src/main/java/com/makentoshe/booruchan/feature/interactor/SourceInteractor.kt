package com.makentoshe.booruchan.feature.interactor

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.EmptySource
import com.makentoshe.booruchan.feature.PluginFactory
import com.makentoshe.booruchan.library.plugin.GetAllPluginsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

typealias SourceId = String

class SourceInteractor @Inject constructor(
    private val pluginFactory: PluginFactory,
    private val findAllPlugins: GetAllPluginsUseCase,
) {
    private val internalSourceFlow = MutableStateFlow<Source>(EmptySource)
    val sourceFlow: StateFlow<Source> get() = internalSourceFlow

    /** find source from plugin by source id and publish it in [sourceFlow] */
    suspend fun getSourceById(sourceId: SourceId): Source? {
        val source = findAllPlugins().map(pluginFactory::buildSource).find { source -> source?.id == sourceId }
        return source?.also { internalSourceFlow.emit(it) }
    }

}