package com.account.manage.accountmanage.user.adapter.`in`.filter

import com.account.manage.accountmanage.common.adpater.out.error.DataNotFoundException
import com.account.manage.accountmanage.common.adpater.out.error.UnAuthorizedException
import com.account.manage.accountmanage.common.adpater.out.error.UserErrorType
import com.account.manage.accountmanage.user.adapter.out.persistence.UserRepository
import com.account.manage.accountmanage.common.infra.auth.LoginObject
import com.account.manage.accountmanage.common.infra.auth.PasswordEncrypt
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest


class LoginFilter(
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
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

        authenticate(parsedLoginObject)

        chain.doFilter(request, response)
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

    private fun authenticate(loginObject: LoginObject) {
        val user = (userRepository.findUserByEmail(loginObject.email)
            ?: throw DataNotFoundException(UserErrorType.NOT_FOUND_USER))

        val password = loginObject.password
        val passwordSalt = user.passwordSalt
        val loginSecuredPassword = PasswordEncrypt.getSecurePassword(loginObject.password, passwordSalt)

        if (password != loginSecuredPassword) {
            throw UnAuthorizedException(UserErrorType.AUTHENTICATION_FAILED)
        }

    }
}