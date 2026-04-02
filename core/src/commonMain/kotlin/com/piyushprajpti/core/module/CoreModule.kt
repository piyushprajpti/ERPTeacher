package com.piyushprajpti.core.module

import androidx.datastore.preferences.core.stringPreferencesKey
import com.piyushprajpti.core.constants.ACCESS_TOKEN
import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.constants.REFRESH_TOKEN
import com.piyushprajpti.core.datastore.PreferenceDataStore
import com.piyushprajpti.core.utils.RefreshTokenRequest
import com.piyushprajpti.core.utils.RefreshTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import co.touchlab.kermit.Logger as KermitLogger
import io.ktor.client.plugins.logging.Logger as KtorLogger

val coreModule = module {
    single<HttpClient> {
        val dataStore: PreferenceDataStore = get()

        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = KtorLogger.DEFAULT
            }

            install(DefaultRequest) {
                url(ApiConstants.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 5000
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = dataStore
                            .getValue(stringPreferencesKey((ACCESS_TOKEN)))
                            .firstOrNull() ?: ""

                        val refreshToken = dataStore
                            .getValue(stringPreferencesKey((REFRESH_TOKEN)))
                            .firstOrNull() ?: ""

                        KermitLogger.e(tag = "TAG") { "Access token: $accessToken" }
                        KermitLogger.e(tag = "TAG") { "Refresh token: $refreshToken" }

                        BearerTokens(accessToken, refreshToken)
                    }

                    refreshTokens {
                        try {
                            val refreshToken = dataStore
                                .getValue(stringPreferencesKey((REFRESH_TOKEN)))
                                .firstOrNull() ?: ""

                            val response = client.post(ApiConstants.Auth.REFRESH_TOKEN) {
                                markAsRefreshTokenRequest()
                                setBody(RefreshTokenRequest(refreshToken))
                            }

                            val newAccessToken = response.body<RefreshTokenResponse>().data.accessToken
                            val newRefreshToken =
                                response.body<RefreshTokenResponse>().data.refreshToken


                            dataStore.setValue(stringPreferencesKey(ACCESS_TOKEN), newAccessToken)
                            dataStore.setValue(stringPreferencesKey(REFRESH_TOKEN), newRefreshToken)

                            KermitLogger.e(tag = "TAG") { "Bearer Token refreshed: $newAccessToken" }

                            BearerTokens(newAccessToken, newRefreshToken)
                        } catch (e: Exception) {
                            KermitLogger.e(tag = "TAG") { "Refresh token failed: ${e.message}" }
                            dataStore.deleteValue(stringPreferencesKey(ACCESS_TOKEN))
                            dataStore.deleteValue(stringPreferencesKey(REFRESH_TOKEN))
                            null
                        }
                    }

                }
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw cause
                }
            }
        }
    }

    single { PreferenceDataStore(get()) }
}
