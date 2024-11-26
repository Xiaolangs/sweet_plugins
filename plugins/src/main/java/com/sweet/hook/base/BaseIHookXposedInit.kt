package com.sweet.hook.base

import android.content.Context
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.sweet.plugins.PluginsHelper

/**
 * description:
 * date: 2024/11/25 13:13
 * author: xiaolang
 */
abstract class BaseIHookXposedInit {
    open var curPluginsHelper: PluginsHelper? = null
    open fun loadInit(baseContext: Context?, pluginsHelper: PluginsHelper) {
        curPluginsHelper = pluginsHelper
        YukiHookAPI.encase(baseContext) {
            load()
        }
    }
    abstract fun PackageParam.load()
}