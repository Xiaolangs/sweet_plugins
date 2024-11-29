package com.sweet.plugins

import android.R.attr.classLoader
import android.util.Log
import com.highcapable.yukihookapi.hook.factory.current
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.sweet.ext.scope
import com.sweet.hook.base.BaseIHookXposedInit
import com.sweet.hook.storage.CoreStorage
import com.sweet.utils.HttpUtils
import com.sweet.utils.LogKTX


/**
 * description:
 * date: 2024/11/25 13:32
 * author: xiaolang
 */
class InitHooker : BaseIHookXposedInit() {
    override fun PackageParam.load() {
        ///这里面就可以开始的插件之旅
        curPluginsHelper?.injectPluginAppResources()
        CoreStorage.getWxId()
        scope {
            HttpUtils.get<String>("").onSuccess { }.onFailure { }
        }
    }
}