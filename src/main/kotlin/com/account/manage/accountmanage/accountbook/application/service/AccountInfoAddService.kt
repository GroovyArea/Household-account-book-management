package com.account.manage.accountmanage.accountbook.application.service

import com.account.manage.accountmanage.accountbook.adapter.out.persistence.AccountBookRepository
import com.account.manage.accountmanage.accountbook.application.port.`in`.AccountInfoAddUseCase
import com.account.manage.accountmanage.accountbook.domain.AccountBook
import com.account.manage.accountmanage.accountbook.model.AccountAddCompletionDto
import com.account.manage.accountmanage.accountbook.model.AccountAddDto
import com.account.manage.accountmanage.accountbook.model.AccountBookRequest
import com.account.manage.accountmanage.accountbook.model.AccountBookResponse
import com.account.manage.accountmanage.user.domain.User
import org.springframework.stereotype.Service

@Service
class AccountInfoAddService(
    private val accountBookRepository: AccountBookRepository,
) : AccountInfoAddUseCase {

    override fun addAccountInfo(
        accountBookRequest: AccountBookRequest, user: User
    ): AccountBookResponse {
        val accountAddDto = accountBookRequest as AccountAddDto
        val accountBook = AccountBook.createAccount(
            accountInfo = accountAddDto.toVo(accountAddDto),
            user = user
        )
        val savedAccountBook = accountBookRepository.save(accountBook)

        return AccountAddCompletionDto(
            accountBookId = savedAccountBook.id,
            createdTime = savedAccountBook.createdAt
        )
    }

}