package com.account.manage.accountmanage.common.adpater.out.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(value = [DataNotFoundException::class])
    fun notFoundHandle(e: AccountManageException) =
        ResponseEntity(createErrorResponse(e.errorType), HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = [UnAuthorizedException::class])
    fun unAuthorizedHandle(e: AccountManageException) =
        ResponseEntity(createErrorResponse(e.errorType), HttpStatus.UNAUTHORIZED)

    @ExceptionHandler(value = [InvalidRequestException::class])
    fun badRequestHandle(e: AccountManageException) =
        ResponseEntity(createErrorResponse(e.errorType), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [NotAllowedException::class])
    fun notAllowedHandle(e: AccountManageException) =
        ResponseEntity(createErrorResponse(e.errorType), HttpStatus.METHOD_NOT_ALLOWED)

    @ExceptionHandler(value = [ConflictException::class])
    fun conflictHandle(e: AccountManageException) =
        ResponseEntity(createErrorResponse(e.errorType), HttpStatus.CONFLICT)

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandle(ex: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val clientInfo = "${request.remoteAddr} (${request.getHeader("user-agent")})"
        val errorMessage =
            "An error occurred during processing of request : [${request.method}] / [${request.requestURI}]. \n Error message : ${ex.message}"

        LOGGER.error("Error occurred while processing request from {} - {}", clientInfo, errorMessage)

        return ResponseEntity(
            ErrorResponse(
                errorCode = ErrorType.INTERNAL_ERROR_CODE,
                errorMessage = ErrorType.INTERNAL_ERROR_DETAIL,
                errorDetails = ErrorType.INTERNAL_ERROR_DETAIL,
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    private fun createErrorResponse(errorType: ErrorType) =
        ErrorResponse(
            errorCode = errorType.getErrorCode(),
            errorMessage = errorType.getErrorName(),
            errorDetails = errorType.getErrorDetails()
        )
}
