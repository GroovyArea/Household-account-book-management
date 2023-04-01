package com.account.manage.accountmanage.accountbook.application.port.`in`

import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.user.domain.User

interface AccountInfoAddUseCase {

    fun addAccountInfo(accountBookRequest: AccountBookRequest, user: User) : AccountBookResponse
}