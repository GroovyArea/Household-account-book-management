package com.account.manage.accountmanage.accountbook.adapter.`in`

import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoUseCase
import com.account.manage.accountmanage.accountbook.model.AccountBookDetailDto
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.accountbook.model.AccountBooksDto
import com.account.manage.accountmanage.common.infra.auth.UserExtractor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/accounts")
class AccountInfoController(
    private val accountInfoUseCase: AccountInfoUseCase,
    private val userExtractor: UserExtractor,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{account-book-id}")
    fun getAccount(
        @PathVariable(value = "account-book-id") accountBookId: Long,
        request: HttpServletRequest,
    ): AccountBookResponse {
        return accountInfoUseCase.getAccountInfo(
            AccountBookDetailDto(
                user = userExtractor.getUserFromRequest(request),
                accountBookId = accountBookId
            )
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getAccountBooks(
        @PageableDefault(page = 0, sort = arrayOf("updatedAt"), direction = Sort.Direction.DESC) pageable: Pageable,
        request: HttpServletRequest,
    ): AccountBookResponse {
        return accountInfoUseCase.getAccountBookList(
            AccountBooksDto(
                user = userExtractor.getUserFromRequest(request),
                pageable = pageable
            )
        )
    }
}