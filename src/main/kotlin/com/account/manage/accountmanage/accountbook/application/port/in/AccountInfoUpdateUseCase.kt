package com.account.manage.accountmanage.accountbook.application.port.`in`

import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse

interface AccountInfoUpdateUseCase {

    fun updateAccountInfo(accountBookRequest: AccountBookRequest, accountBookId: Long): AccountBookResponse

    fun deleteAccount(accountBookId: Long): AccountBookResponse
}