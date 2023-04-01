package com.account.manage.accountmanage.common.adpater.out.error

interface ErrorType {

    companion object {
        const val INTERNAL_ERROR_CODE: Int = 200000
        const val INTERNAL_ERROR_DETAIL: String = "Internal Server Error."
    }

    fun getErrorCode(): Int
    fun getErrorName(): String
    fun getErrorDetails() : String
}