package com.account.manage.accountmanage.common.adpater.out.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ErrorHandlingFilter : OncePerRequestFilter() {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: RuntimeException) {
            createRuntimeExceptionResponse(e, response)
        } catch (e: Exception) {
            createExceptionResponse(response)
        }
    }

    private fun createRuntimeExceptionResponse(ex: RuntimeException, response: HttpServletResponse) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.writer.println(ex.message)
    }

    private fun createExceptionResponse(response: HttpServletResponse) {
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        response.writer.println("Internal server error.")
    }
}