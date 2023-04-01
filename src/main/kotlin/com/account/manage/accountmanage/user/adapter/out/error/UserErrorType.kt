package com.account.manage.accountmanage.user.adapter.out.error

import com.account.manage.accountmanage.common.adpater.out.error.ErrorType

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