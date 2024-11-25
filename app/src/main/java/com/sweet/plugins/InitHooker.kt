package com.sweet.plugins

import android.app.Activity
import android.content.Context
import android.util.Log
import com.highcapable.yukihookapi.hook.core.YukiMemberHookCreator
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.type.android.ActivityClass
import com.sweet.hook.base.BaseIHookXposedInit

/**
 * description:
 * date: 2024/11/25 13:32
 * author: xiaolang
 */
class InitHooker: BaseIHookXposedInit() {
    override fun PackageParam.load() {
        Log.e("PluginTest", "test: ------2")
        "com.tencent.mm.pluginsdk.ui.chat.ChatFooter"
            .toClass(PluginsUtils.getWxClassLoader())
            .constructor()
            .hookAll {
                after {
                    Log.e("PluginTest", "test: ------3")
                }
            }
    }
}