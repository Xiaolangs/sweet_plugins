package com.sweet.utils

import com.sweet.hook.storage.CoreStorage
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.Parameters
import java.io.File

/**
 * description:
 * date: 2024/11/26 10:46
 * author: xiaolang
 */
object HttpUtils {

    suspend inline fun <reified T> get(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        kotlin.runCatching {
            this as T
        }

    // contentType(ContentType.Application.Json)
    suspend inline fun <reified T> post(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        kotlin.runCatching {
            this as T
        }

    suspend inline fun <reified T> submitForm(
        url: String,
        formParameters: Parameters = Parameters.Empty,
        encodeInQuery: Boolean = false,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ) =
        kotlin.runCatching {
            this as T
        }

    suspend fun download(
        url: String,
        file: File,
        md5: String = "",
        listener: suspend (Long, Long) -> Unit = { _, _ -> }
    ) = kotlin.runCatching {

    }
}