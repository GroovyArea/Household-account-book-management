package com.account.manage.accountmanage.accountbook.adapter.`in`

import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoRecoverUseCase
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class AccountRecoverController(
    private val accountInfoRecoverUseCase: AccountInfoRecoverUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/recovery/{account-book-id}")
    fun recoverAccount(
        @PathVariable(value = "account-book-id") accountBookId: Long
    ): AccountBookResponse {
        return accountInfoRecoverUseCase.accountInfoRecover(accountBookId)
    }
}