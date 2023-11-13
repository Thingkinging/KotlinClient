package com.kwh.dailyq.api

import com.kwh.dailyq.AuthManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        val authType = request.tag(AuthType::class.java) ?: AuthType.ACCESS_TOKEN
        when (authType) {
            AuthType.NO_AUTH -> {

            }
            AuthType.ACCESS_TOKEN -> {
                AuthManager.accessToken?.let { token ->
                    builder.header("Authorization", "Bearer $token")
                }
            }
        }

        AuthManager.accessToken?.let { token ->
            builder.header("Authorization", "Bearer $token")
        }
        return chain.proceed(builder.build())
    }
}