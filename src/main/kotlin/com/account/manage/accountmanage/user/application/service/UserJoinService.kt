package com.account.manage.accountmanage.user.application.service

import com.account.manage.accountmanage.common.adpater.out.error.InvalidRequestException
import com.account.manage.accountmanage.common.infra.auth.CookieCreator
import com.account.manage.accountmanage.common.infra.auth.JwtProvider
import com.account.manage.accountmanage.common.infra.auth.PasswordEncrypt
import com.account.manage.accountmanage.user.adapter.out.error.UserErrorType
import com.account.manage.accountmanage.user.adapter.out.persistence.UserRepository
import com.account.manage.accountmanage.user.application.port.`in`.UserJoinUseCase
import com.account.manage.accountmanage.user.model.JoinCompletionDto
import com.account.manage.accountmanage.user.model.UserJoinDto
import com.account.manage.accountmanage.user.model.UserRequest
import com.account.manage.accountmanage.user.model.UserResponse
import org.springframework.stereotype.Service

@Service
class UserJoinService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val cookieCreator: CookieCreator,
) : UserJoinUseCase {

    override fun signUpUser(userRequest: UserRequest): UserResponse {
        val userJoinDto = userRequest as UserJoinDto
        duplicatedCheck(userJoinDto)

        val salt = PasswordEncrypt.getSalt()
        val securePassword = PasswordEncrypt.getSecurePassword(userJoinDto.password, salt)
        val user = userJoinDto.toEntity(userJoinDto)
        user.updatePasswordInfo(securePassword, salt)

        val savedUser = userRepository.save(user)

        return JoinCompletionDto(
            accessToken = jwtProvider.generateAccessToken(savedUser.id),
            refreshTokenCookieValue = cookieCreator.createRefreshCookie(jwtProvider.generateRefreshToken(savedUser.id))
        )
    }

    private fun duplicatedCheck(userJoinDto: UserJoinDto) {
        if (userRepository.existsByEmail(userJoinDto.email))
            throw InvalidRequestException(UserErrorType.USER_ALREADY_EXISTS)
    }
}