package com.sweet.hook.base

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

/**
 * description:
 * date: 2024/11/25 09:41
 * author: xiaolang
 */
abstract class BaseHooker : YukiBaseHooker() {
    abstract fun hook()
    override fun onHook() {

    }
}