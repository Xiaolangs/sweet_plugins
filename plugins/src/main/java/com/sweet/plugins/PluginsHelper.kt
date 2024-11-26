package com.sweet.plugins

import android.content.Context
import android.content.res.Resources
import com.highcapable.yukihookapi.hook.factory.current
import dalvik.system.DexClassLoader

/**
 * description:
 * date: 2024/11/25 13:42
 * author: xiaolang
 */
class PluginsHelper(val context: Context, private val pluginsApkPath: String) {
    private var pluginResources: Resources? = null
    private var pluginClassLoader: DexClassLoader? = null

    fun getPluginResources() = pluginResources

    fun getPluginClassLoader() = pluginClassLoader

    fun injectPluginAppResources() {

    }

}