package com.account.manage.accountmanage.accountbook.application.port.`in`

import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.user.domain.User
import org.springframework.data.domain.Pageable

interface AccountInfoUseCase {

    fun getAccountInfo(accountBookId: Long): AccountBookResponse

    fun getAccountBookList(accountBookRequest: AccountBookRequest): AccountBookResponse

}