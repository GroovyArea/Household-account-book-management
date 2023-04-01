package com.account.manage.accountmanage.adapter.`in`.filter

import com.account.manage.accountmanage.common.adpater.out.error.DataNotFoundException
import com.account.manage.accountmanage.common.adpater.out.error.InvalidRequestException
import com.account.manage.accountmanage.common.adpater.out.error.UserErrorType
import com.account.manage.accountmanage.user.adapter.out.persistence.UserRepository
import com.account.manage.accountmanage.common.infra.auth.JwtProvider
import com.account.manage.accountmanage.common.infra.auth.JwtValidator
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter(
    private val jwtValidator: JwtValidator,
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
) : OncePerRequestFilter() {

    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val AUTH_HEADER = "Authorization"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(AUTH_HEADER)

        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            val token = authHeader.substring(7)
            if (!jwtValidator.validateAccessToken(token)) {
                throw InvalidRequestException(UserErrorType.AUTHENTICATION_FAILED)
            }
            setUserInRequest(token, request)
        }

        filterChain.doFilter(request, response)
    }


    private fun setUserInRequest(token: String, request: HttpServletRequest) {
        val userId = jwtProvider.getUserIdFromToken(token)
        val user = (userRepository.findUserById(userId)
            ?: throw DataNotFoundException(UserErrorType.NOT_FOUND_USER))

        request.setAttribute("user", user)
    }
}