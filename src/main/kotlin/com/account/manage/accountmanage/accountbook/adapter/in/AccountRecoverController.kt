package com.account.manage.accountmanage.accountbook.adapter.`in`

import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoRecoverUseCase
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.accountbook.model.AccountRecoverDto
import com.account.manage.accountmanage.common.infra.auth.UserExtractor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/accounts")
class AccountRecoverController(
    private val userExtractor: UserExtractor,
    private val accountInfoRecoverUseCase: AccountInfoRecoverUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/recovery/{account-book-id}")
    fun recoverAccount(
        request: HttpServletRequest,
        @PathVariable(value = "account-book-id") accountBookId: Long
    ): AccountBookResponse {
        return accountInfoRecoverUseCase.accountInfoRecover(
            AccountRecoverDto(
                user = userExtractor.getUserFromRequest(request),
                accountBookId = accountBookId
            )
        )
    }
}