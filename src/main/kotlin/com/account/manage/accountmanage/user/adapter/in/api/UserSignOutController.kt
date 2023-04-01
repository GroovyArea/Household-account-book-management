package com.account.manage.accountmanage.user.adapter.`in`.api

import com.account.manage.accountmanage.user.application.port.`in`.UserSignOutUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/users")
class UserSignOutController(
    private val userSignOutUseCase: UserSignOutUseCase
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/sign-out")
    fun signOut(
        response: HttpServletResponse
    ) {
        val signOutCookie = userSignOutUseCase.signOutUser()
        response.setHeader("auth-cookie", signOutCookie.toString())
    }
}
