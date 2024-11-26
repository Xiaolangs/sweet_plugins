package com.sweet.utils

import com.sweet.ext.md5check
import com.sweet.ext.smartCreateNewFile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.statement.bodyAsText
import io.ktor.content.ByteArrayContent
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentLength
import io.ktor.http.encodedPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.isEmpty
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.content
import kotlinx.serialization.serializer
import okhttp3.EventListener
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * description:
 * date: 2024/8/9 20:40
 * author: xiaolang
 */
object HttpUtils {
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = false
    }

    val client = HttpClient(OkHttp) {
        engine {

        }
        install(ContentNegotiation) {
            json(json = json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 300000
            connectTimeoutMillis = 300000
        }

    }

    suspend inline fun <reified T> get(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        kotlin.runCatching {
            client.get(url, block).body<T>()
        }

    // contentType(ContentType.Application.Json)
    suspend inline fun <reified T> post(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        kotlin.runCatching {
            client.post(url, block).body<T>()
        }

    suspend inline fun <reified T> submitForm(
        url: String,
        formParameters: Parameters = Parameters.Empty,
        encodeInQuery: Boolean = false,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ) =
        kotlin.runCatching {
            client.submitForm(url, formParameters, encodeInQuery, block).body<T>()
        }

    suspend fun download(
        url: String,
        file: File,
        md5: String = "",
        listener: suspend (Long, Long) -> Unit = { _, _ -> }
    ) = kotlin.runCatching {
        val httpResponse: HttpResponse = client.get(url) {
            onDownload(listener)
        }
        val responseBody: ByteArray = httpResponse.body()
        file.smartCreateNewFile()
        file.writeBytes(responseBody)
        if (md5.isNotEmpty()) {
            if (!file.md5check(md5)) throw Exception("MD5 校验失败")
        }
    }
}