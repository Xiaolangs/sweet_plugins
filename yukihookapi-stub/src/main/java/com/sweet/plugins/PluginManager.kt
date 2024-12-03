package com.sweet.plugins

import java.io.File

/**
 * description:
 * date: 2024/11/26 18:01
 * author: xiaolang
 */

data class Plugin(val patch: String, val isEnable: Boolean = true, val helper: PluginsHelper)

object PluginManager {
    private val pluginsHelperList: HashMap<String, PluginsHelper> = hashMapOf()

    fun getPluginsHelper(pluginFile: File): PluginsHelper? {
        return pluginsHelperList[pluginFile.absolutePath]
    }
}