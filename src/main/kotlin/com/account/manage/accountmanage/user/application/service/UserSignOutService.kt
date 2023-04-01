package com.account.manage.accountmanage.user.application.service

import com.account.manage.accountmanage.common.infra.auth.CookieCreator
import com.account.manage.accountmanage.user.application.port.`in`.UserSignOutUseCase
import com.account.manage.accountmanage.user.model.SignOutDto
import com.account.manage.accountmanage.user.model.UserRequest
import com.account.manage.accountmanage.user.model.UserResponse
import org.springframework.stereotype.Service

@Service
class UserSignOutService(
    private val cookieCreator: CookieCreator,
) : UserSignOutUseCase {

    override fun signOutUser(): UserResponse {
        val signOutCookie = cookieCreator.getAccessCookieOfSignOut()
        return SignOutDto(signOutCookie)
    }
}