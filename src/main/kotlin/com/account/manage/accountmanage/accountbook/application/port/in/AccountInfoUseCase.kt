package com.account.manage.accountmanage.accountbook.application.port.`in`

import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse


interface AccountInfoUseCase {

    fun getAccountInfo(accountBookRequest: AccountBookRequest): AccountBookResponse

    fun getAccountBookList(accountBookRequest: AccountBookRequest): AccountBookResponse

}