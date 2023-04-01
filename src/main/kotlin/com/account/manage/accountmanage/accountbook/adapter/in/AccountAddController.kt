package com.account.manage.accountmanage.accountbook.adapter.`in`

import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoAddUseCase
import com.account.manage.accountmanage.accountbook.model.AccountAddDto
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.common.infra.auth.UserExtractor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/accounts")
class AccountAddController(
    private val accountInfoAddUseCase: AccountInfoAddUseCase,
    private val userExtractor: UserExtractor,
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun addAccount(
        request: HttpServletRequest,
        @Valid @RequestBody accountAddDto: AccountAddDto,
    ): AccountBookResponse {
        return accountInfoAddUseCase.addAccountInfo(
            accountBookRequest = accountAddDto,
            user = userExtractor.getUserFromRequest(request)
        )
    }

}