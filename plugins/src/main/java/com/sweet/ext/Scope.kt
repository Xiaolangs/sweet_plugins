package com.sweet.ext

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * description:
 * date: 2024/8/9 22:36
 * author: xiaolang
 */


fun scope(
    dispatcher: CoroutineContext = Dispatchers.Main,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineScope(dispatcher).launch(block = block)
}