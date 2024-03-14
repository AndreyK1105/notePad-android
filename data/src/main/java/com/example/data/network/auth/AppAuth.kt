package com.example.data.network.auth

import android.net.Uri
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

object AppAuth {
    private val serviceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(AuthConfig.AUTH_URI),
        Uri.parse(AuthConfig.TOKEN_URI),
        null,
        //Uri.parse(AuthConfig.END_SESSION_URI)

    )

    fun getAuthRequest(): AuthorizationRequest {
        val redirectUri = AuthConfig.CALLBACK_URL.toUri()
        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .build()
    }


    private object AuthConfig {
        const val AUTH_URI = "https://accounts.google.com/o/oauth2/auth"
        const val TOKEN_URI = "https://oauth2.googleapis.com/token"
        const val END_SESSION_URI = ""
        const val SCOPE = "https://www.googleapis.com/auth/calendar.events.readonly"
        const val RESPONSE_TYPE = ResponseTypeValues.CODE

        const val CLIENT_ID =
            "81991847530-25eflpu836sravbeu939ahf079p890t8.apps.googleusercontent.com"

        // const val CLIENT_SECRET = "..."5
        const val CALLBACK_URL = "com.example.mynotepad:/"
        const val LOGOUT_CALLBACK_URL = "ru.kts.oauth://github.com/logout_callback"
    }
}
