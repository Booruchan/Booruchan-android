package com.makentoshe.booruchan.feature

import org.booruchan.extension.sdk.Source
import com.makentoshe.booruchan.feature.entity.Plugin
import javax.inject.Inject

class PluginFactory @Inject constructor() {

    fun buildSource(plugin: Plugin): Source? {
        val instance = plugin.sourceClass.getDeclaredConstructor().newInstance()
        return SourceWrapper(instance as? Source ?: return null)
    }

}
