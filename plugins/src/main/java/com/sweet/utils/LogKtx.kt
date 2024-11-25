package com.sweet.utils

/**
 * description:
 * date: 2024/11/25 23:43
 * author: xiaolang
 */

object LogKTX {
    data class LogRecord(val tag: String, val content: String)

    private val currentRecordLog = arrayListOf<LogRecord>()

    /**
     * 获取当前调用栈
     */
    private fun getMethodStack(): String {
        val stackTraceElements = Thread.currentThread().stackTrace
        val stringBuilder = StringBuilder()
        for (temp in stackTraceElements) {
            val line = temp.toString()
            stringBuilder.append(line)
            stringBuilder.append("\n")
        }

        return stringBuilder.toString()
    }

    fun eStack(tag: String, vararg logs: Any?) {
        e(tag, *logs, getMethodStack())
    }

    fun getRecordText() = currentRecordLog.joinToString(separator = "\n") {
        "${it.tag}->>${it.content}"
    }

    fun record(tag: String, content: String) {

    }

    fun e(tag: String, vararg logs: Any?) {

    }

}