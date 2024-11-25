package com.sweet.hook.base

import android.content.Context
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.param.PackageParam

/**
 * description:
 * date: 2024/11/25 13:13
 * author: xiaolang
 */
abstract class BaseIHookXposedInit {
    private fun loadInit(baseContext: Context?) {
        YukiHookAPI.encase(baseContext) {
            load()
        }
    }
    abstract fun load()
}