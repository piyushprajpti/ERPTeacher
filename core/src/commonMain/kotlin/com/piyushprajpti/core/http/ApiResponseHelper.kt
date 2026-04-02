package com.piyushprajpti.core.http

import co.touchlab.kermit.Logger
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.coroutines.cancellation.CancellationException

object ApiResponseHelper {
    @PublishedApi
    internal val json = Json { ignoreUnknownKeys = true }

    suspend inline fun <reified T : Any> handleApiResponse(
        crossinline block: suspend () -> HttpResponse
    ): Result<T> {
        return try {
            val response = block()
            val responseBody = response.bodyAsText()

            logApiCall(response, responseBody)

            if (response.status.isSuccess()) {
                val data: T = if (T::class == Unit::class) {
                    Unit as T
                } else {
                    json.decodeFromString(responseBody)
                }
                Result.Success(data)
            } else {
                val apiException = mapHttpResponseToApiException(response, responseBody)
                Result.Error(apiException)
            }
        } catch (exception: ApiException) {
            Logger.e(tag = "API_LOG") { "api exception -> $exception" }
            Result.Error(exception)
        } catch (exception: ResponseException) {
            val response = exception.response
            val responseBody = runCatching { response.bodyAsText() }.getOrNull() ?: ""
            logApiCall(response, responseBody)

            val apiException = mapHttpResponseToApiException(response, responseBody)
            Result.Error(apiException)
        } catch (exception: Exception) {
            if (exception is CancellationException) throw exception
            Logger.e(tag = "API_LOG") { "error response -> $exception" }
            val apiException = when (exception) {
                is ConnectTimeoutException,
                is SocketTimeoutException,
                is UnresolvedAddressException -> ApiException(
                    error = ErrorCode.NO_INTERNET,
                    message = "No internet connection. Please check your network and try again."
                )

                else -> ApiException(
                    error = ErrorCode.UNKNOWN,
                    message = exception.message ?: "Something went wrong"
                )
            }
            Result.Error(apiException)
        }
    }

    @PublishedApi
    internal fun logApiCall(response: HttpResponse, responseBody: String) {
        val request = response.request
        val requestBody = when (val content = request.content) {
            is TextContent -> content.text
            is OutgoingContent -> "Binary/Other Content"
            else -> "No Body"
        }

        Logger.d(tag = "TAG") {
            """
            |
            |---- API CALL START ----
            |Request URL: ${request.url}
            |Request Method: ${request.method.value}
            |Request Body: $requestBody
            |Response Status: ${response.status.value} ${response.status.description}
            |Response Body: $responseBody
            |---- API CALL END ----
            |
            """.trimMargin()
        }
    }

    @PublishedApi
    internal suspend fun mapHttpResponseToApiException(
        response: HttpResponse,
        errorBody: String? = null
    ): ApiException {
        val finalErrorBody = errorBody ?: runCatching { response.bodyAsText() }.getOrNull()
        val statusCode = response.status.value
        val parsedPayload = parseErrorPayload(finalErrorBody)
        val errorCode = parsedPayload?.code?.let(ErrorCode::fromValue) ?: mapStatusToErrorCode(statusCode)
        val message = parsedPayload?.message ?: "Error: $statusCode"

        return ApiException(error = errorCode, message = message)
    }

    @PublishedApi
    internal fun mapStatusToErrorCode(statusCode: Int): ErrorCode {
        return when (statusCode) {
            401, 403 -> ErrorCode.AUTH_ERROR
            404 -> ErrorCode.NOT_FOUND
            else -> ErrorCode.UNKNOWN
        }
    }

    @PublishedApi
    internal fun parseErrorPayload(rawBody: String?): ParsedErrorPayload? {
        if (rawBody.isNullOrBlank()) return null

        return runCatching {
            val root = json.parseToJsonElement(rawBody)
            ParsedErrorPayload(
                message = extractString(root, "message")
                    ?: extractString(root, "error")
                    ?: extractString(root, "detail")
                    ?: extractFirstArrayMessage(root),
                code = extractString(root, "code")
                    ?: extractString(root, "errorCode")
                    ?: extractString(root, "error_code")
            )
        }.getOrNull()
    }

    @PublishedApi
    internal fun extractString(root: JsonElement, key: String): String? {
        val value = root.jsonObject[key]?.jsonPrimitive?.contentOrNull
        return value?.takeIf { it.isNotBlank() }
    }

    @PublishedApi
    internal fun extractFirstArrayMessage(root: JsonElement): String? {
        val errors = root.jsonObject["errors"]?.jsonArray ?: return null
        return errors.firstOrNull()?.jsonPrimitive?.contentOrNull?.takeIf { it.isNotBlank() }
    }

    @PublishedApi
    internal data class ParsedErrorPayload(
        val message: String?,
        val code: String?
    )
}
