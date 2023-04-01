package com.account.manage.accountmanage.user.adapter.`in`.api

import com.account.manage.accountmanage.user.application.port.`in`.UserJoinUseCase
import com.account.manage.accountmanage.user.model.JoinCompletionDto
import com.account.manage.accountmanage.user.model.UserJoinDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserSignInController(
    private val userJoinUseCase: UserJoinUseCase,
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    fun join(
        response: HttpServletResponse,
        @Valid @RequestBody userJoinDto: UserJoinDto
    ) {
        val joinCompletionDto = userJoinUseCase.signUpUser(userJoinDto) as JoinCompletionDto
        val accessToken = joinCompletionDto.accessToken
        val refreshTokenCookieValue = joinCompletionDto.refreshTokenCookieValue

        response.addHeader("Authorization", "Bearer $accessToken")
        response.addHeader("auth-cookie", refreshTokenCookieValue)

    }
}