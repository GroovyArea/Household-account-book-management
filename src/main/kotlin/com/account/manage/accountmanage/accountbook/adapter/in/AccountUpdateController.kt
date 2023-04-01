package com.account.manage.accountmanage.accountbook.adapter.`in`

import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoUpdateUseCase
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.accountbook.model.AccountUpdateDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/accounts")
class AccountUpdateController(
    private val accountInfoUpdateUseCase: AccountInfoUpdateUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{account-book-id}")
    fun updateAccountBook(
        @PathVariable(value = "account-book-id") accountBookId: Long,
        @Valid @RequestBody accountUpdateDto: AccountUpdateDto,
    ): AccountBookResponse {
        return accountInfoUpdateUseCase.updateAccountInfo(
            accountBookRequest = accountUpdateDto,
            accountBookId = accountBookId
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{account-book-id}")
    fun deleteAccountBook(
        @PathVariable(value = "account-book-id") accountBookId: Long,
    ): AccountBookResponse {
        return accountInfoUpdateUseCase.deleteAccount(accountBookId)
    }
}