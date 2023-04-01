package com.account.manage.accountmanage.common.adpater.out.error

sealed interface ErrorType {

    companion object {
        const val INTERNAL_ERROR_CODE: Int = 200000
        const val INTERNAL_ERROR_DETAIL: String = "Internal Server Error."
    }

    fun getErrorCode(): Int
    fun getErrorName(): String
    fun getErrorDetails() : String
}

enum class UserErrorType(
    private val code: Int,
    private val message: String,
) : ErrorType {

    NOT_FOUND_USER(10000, "Not found user."),
    AUTHENTICATION_FAILED(10001, "Authentication failed."),
    USER_ALREADY_EXISTS(10002, "User already exists.");

    override fun getErrorCode() = this.code

    override fun getErrorName() = this.name
    override fun getErrorDetails() = this.message
}
