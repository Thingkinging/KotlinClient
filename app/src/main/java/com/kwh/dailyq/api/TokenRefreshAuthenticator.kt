package com.kwh.dailyq.api

import com.kwh.dailyq.AuthManager
import com.kwh.dailyq.api.response.AuthToken
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenRefreshAuthenticator() : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = response.request.header("Authorization")
            ?.split(" ")
            ?.getOrNull(1)
        accessToken ?: return null
        AuthManager.refreshToken ?: return null

        val api = ApiService.getInstance()

        synchronized(this) {
            if (accessToken == AuthManager.accessToken) {
                val authTokenResponse =
                    api.refreshToken(AuthManager.refreshToken!!).execute().body()!!

                AuthManager.accessToken = authTokenResponse.accessToken
                AuthManager.refreshToken = authTokenResponse.refreshToken
            }
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${AuthManager.accessToken}")
            .build()

    }
}