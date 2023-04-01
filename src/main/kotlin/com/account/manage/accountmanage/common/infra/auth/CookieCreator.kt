package com.account.manage.accountmanage.common.infra.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class CookieCreator(
    @Value("\${spring.cookie.env-domain}") private val envDomain: String,
    @Value("\${spring.cookie.same-site}") private val sameSite: String?,
) {
    fun createRefreshCookie(refreshToken: String): String {
        val cookie = ResponseCookie.from("access-cookie", refreshToken)
            .domain(envDomain)
            .path("/")
            .maxAge(60 * 60 * 24 * 28)
            .secure(true)
            .httpOnly(true)
            .sameSite(sameSite)
            .build()

        return cookie.toString()
    }

    fun getAccessCookieOfSignOut(): ResponseCookie {
        return ResponseCookie.from("access-cookie", "good-bye")
            .domain(envDomain)
            .path("/")
            .maxAge(0)
            .secure(true)
            .httpOnly(true)
            .sameSite(sameSite)
            .build()
    }
}