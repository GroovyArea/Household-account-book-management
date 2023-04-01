package com.account.manage.accountmanage.user.model

import org.springframework.expression.spel.ast.StringLiteral
import org.springframework.http.ResponseCookie

sealed interface UserResponse

data class JoinCompletionDto(
    val accessToken: String,
    val refreshTokenCookieValue: String,
) : UserResponse

data class SignOutDto(
    val accessCookie: ResponseCookie
) : UserResponse
