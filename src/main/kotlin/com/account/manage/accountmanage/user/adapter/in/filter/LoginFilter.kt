package com.account.manage.accountmanage.user.adapter.`in`.filter

import com.account.manage.accountmanage.common.adpater.out.error.DataNotFoundException
import com.account.manage.accountmanage.common.adpater.out.error.UnAuthorizedException
import com.account.manage.accountmanage.common.infra.auth.CookieCreator
import com.account.manage.accountmanage.common.infra.auth.JwtProvider
import com.account.manage.accountmanage.user.adapter.out.persistence.UserRepository
import com.account.manage.accountmanage.common.infra.auth.LoginObject
import com.account.manage.accountmanage.common.infra.auth.PasswordEncrypt
import com.account.manage.accountmanage.user.adapter.out.error.UserErrorType
import com.account.manage.accountmanage.user.domain.User
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class LoginFilter(
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
    private val cookieCreator: CookieCreator,
) : Filter {

    override fun init(filterConfig: FilterConfig) {

    }

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val parsedLoginObject = try {
            parseObject(request as HttpServletRequest)
        } catch (e: IOException) {
            throw UnAuthorizedException(UserErrorType.AUTHENTICATION_FAILED)
        }

        val user = authenticate(parsedLoginObject)
        addTokenToHeader(response, user)
        return
    }

    override fun destroy() {

    }

    private fun parseObject(request: HttpServletRequest): LoginObject {
        val reader = request.reader
        return objectMapper.readValue(
            reader,
            LoginObject::class.java
        )
    }

    private fun authenticate(loginObject: LoginObject): User {
        val user = (userRepository.findUserByEmail(loginObject.email)
            ?: throw DataNotFoundException(UserErrorType.NOT_FOUND_USER))

        val password = loginObject.password
        val passwordSalt = user.passwordSalt
        val loginSecuredPassword = PasswordEncrypt.getSecurePassword(loginObject.password, passwordSalt)

        if (password != loginSecuredPassword) {
            throw UnAuthorizedException(UserErrorType.AUTHENTICATION_FAILED)
        }

        return user
    }

    private fun addTokenToHeader(
        response: ServletResponse,
        user: User
    ) {
        val httpServletResponse = response as HttpServletResponse

        val accessToken = jwtProvider.generateAccessToken(user.id)
        val refreshToken = jwtProvider.generateRefreshToken(user.id)

        httpServletResponse.addHeader("Authorization", "Bearer $accessToken")
        httpServletResponse.addHeader("set-cookie", cookieCreator.createRefreshCookie(refreshToken).toString())
    }


}