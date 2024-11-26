package com.sweet.plugins

import android.R.attr.classLoader
import android.util.Log
import com.highcapable.yukihookapi.hook.factory.current
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.sweet.ext.scope
import com.sweet.hook.base.BaseIHookXposedInit
import com.sweet.utils.HttpUtils
import com.sweet.utils.LogKTX


/**
 * description:
 * date: 2024/11/25 13:32
 * author: xiaolang
 */
class InitHooker : BaseIHookXposedInit() {
    override fun PackageParam.load() {
        Log.e("PluginTest", "test: ------2")

        "com.tencent.mm.ui.chatting.viewitems.l0".toClass(PluginsUtils.getWxClassLoader()).method {
            name("a")
        }.hook {
            after {
                //   w3Var.c(1, 0, 1, rVar.f396394d.getString(R.string.ajf), R.raw.icons_filled_eyes_off_new);
                args[0]?.current()?.method {
                    name("c")
                }?.call(
                    1, 0, 1, "哑巴", android.R.drawable.btn_radio
                )
                scope {
                    HttpUtils.get<String>("https://applet.xiaojieyahu.top/tianluojson/wx_config53.json").onSuccess {
                        Log.e("PluginTest", "test: ------$it")
                    }
                }
            }
        }
    }
}