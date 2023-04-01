package com.account.manage.accountmanage.accountbook.adapter.out.error

import com.account.manage.accountmanage.common.adpater.out.error.ErrorType

enum class AccountBookErrorType(
    private val code: Int,
    private val message: String,
) : ErrorType {

    NOT_FOUND_ACCOUNT_BOOK(20000, "Not found account book."),;


    override fun getErrorCode() = this.code

    override fun getErrorName() = this.name
    override fun getErrorDetails() = this.message
}